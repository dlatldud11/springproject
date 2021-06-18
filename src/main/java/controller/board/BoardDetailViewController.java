package controller.board;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import bean.Board;
import bean.Member;
import controller.common.SuperClass;
import dao.BoardDao;
import utility.FlowParameters;

@Controller
public class BoardDetailViewController extends SuperClass{
	private final String command = "/detailview.bo" ; 
	private ModelAndView mav = null ;
	private String redirect = "redirect:/list.bo" ;
	
	@Autowired
	@Qualifier("bdao")
	private BoardDao bdao ;
	
	public BoardDetailViewController() {
		super("boDetailView", "boList");
		this.mav = new ModelAndView();
	}
	
	@GetMapping(command)
	public ModelAndView doGet(
			@RequestParam(value = "no", required = true) int no,
			@RequestParam(value = "pageNumber", required = false) String pageNumber, 
			@RequestParam(value = "pageSize", required = false) String pageSize,
			@RequestParam(value = "mode", required = false) String mode,
			@RequestParam(value = "keyword", required = false) String keyword,
			HttpSession session){
		
		Board bean = bdao.SelectDataByPk(no) ;
		
		FlowParameters parameters 
			= new FlowParameters(pageNumber, pageSize, mode, keyword);
		
		System.out.println(this.getClass() + " : " + parameters.toString());
		
		if (bean != null) { 
			// 작성자의 게시물이 아니면 조회수를 +1 증가시킵니다.
			// bean.getWriter()와 세션의 loginfo의 id를 비교합니다.
			// 동일하지 않으면 조회수를 +1
			
			// login : 현재 접속한 사람의 정보를 저장하고 있는 객체입니다.
			Member login =  (Member)session.getAttribute("loginfo") ;
			
			if(bean.getWriter() == null || !bean.getWriter().equals(login.getId())) {
				bdao.UpdateReadhit(no) ;
			}
			mav.addObject("bean", bean);
			mav.addObject("parameters", parameters.toString());
			
			//상세 보기 페이지로 이동
			this.mav.setViewName(super.getpage);
		} else {
			// 포워딩을 이용하여 목록 페이지로 다시 돌아갑니다.
			// 다음과 같이 코딩하면 request와 response 객체가 그대로 다시 넘어 갑니다.
			this.mav.setViewName(this.redirect);
		}
		return this.mav ;
	}
}
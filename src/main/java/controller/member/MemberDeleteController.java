package controller.member;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import bean.Member;
import controller.common.SuperClass;
import dao.MemberDao;
@Controller
public class MemberDeleteController extends SuperClass{
	private final String command = "/delete.me";
	private ModelAndView mav = null;
	private final String redirect = "redirect:/insert.me";
	
	@Autowired
	@Qualifier("mdao")
	private MemberDao mdao;
	
	public MemberDeleteController() {
		super("meLoginForm",null);
		this.mav = new ModelAndView();
	}
	
	@GetMapping(command)
	public ModelAndView doGet(
			@RequestParam(value ="id", required = true)String id,
			HttpSession session) {
		// 회원 탈퇴시, 과거 주문 내역과 작성했던 게시물 내역에 대한 수정이 필요합니다.
		// 그래서, deletedata 메소드에 member 객체를 매개 변수로 넘겨 주어야 합니다.
		
		Member bean = mdao.SelectDataByPk(id);
		
		int cnt = -99999;
		cnt = mdao.DeleteData(bean);
		// 나의 세션 정보를 클리어 해야 합니다.
		session.invalidate();
		this.mav.setViewName(redirect);
		return this.mav;
	}
	
}

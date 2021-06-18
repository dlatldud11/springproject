package controller.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import bean.Member;
import controller.common.SuperClass;
import dao.MemberDao;
@Controller
public class MemberIdCheckController extends SuperClass{
	private final String command = "/idcheck.me";
	private ModelAndView mav = null;
	
	@Autowired
	@Qualifier("mdao")
	private MemberDao mdao;
	
	public MemberIdCheckController() {
		super("idCheck",null);
		this.mav = new ModelAndView();
	}
	
	@GetMapping(command)
	public ModelAndView doGet(@RequestParam(value="id", required = true)String id) {
		Member bean = this.mdao.SelectDataByPk(id);
		
		if (bean != null) { // 존재하는 경우
			if (bean.getId().equalsIgnoreCase("admin")) { // 관리자인 경우
				this.mav.addObject("message","admin은(는) <font color='red'><b>사용 불가능</b></font>한 아이디입니다.");
				this.mav.addObject("isCheck" , false);
			} else { // 일반 사용자인 경우
				this.mav.addObject("message",id + "은(는) <font color='red'><b>사용중</b></font>인 아이디입니다.");
				this.mav.addObject("isCheck" , false);
			}
		} else { // 존재 하지 않는 경우
			this.mav.addObject("message",id + "은(는) <font color='blue'><b>사용 가능</b></font>한 아이디입니다.");
			this.mav.addObject("isCheck" , true);
		}
		this.mav.setViewName(super.getpage);
		return this.mav;
	}
	
}

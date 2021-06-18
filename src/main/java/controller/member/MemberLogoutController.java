package controller.member;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import controller.common.SuperClass;
import dao.MemberDao;

@Controller
public class MemberLogoutController extends SuperClass{
	private final String command = "/logout.me";
	private ModelAndView mav = null;
	
	@Autowired
	@Qualifier("mdao")
	private MemberDao mdao;
	
	public MemberLogoutController() {
		super("meLoginForm","boList");
		this.mav = new ModelAndView();
	}
	
	@GetMapping(command)
	public ModelAndView doGet(HttpSession session) {
		// 세션 공간에 장바구니 정보가 있으면
		// 장바구니 임시 테이블에 저장합니다.
		
		// 세션 영역을 완전히 삭제하도록 합니다.
		session.invalidate(); // 세션 내용 다 지우기
		
		// 로그인 페이지로 다시 이동합니다.
		this.mav.setViewName(super.getpage);
		return this.mav;
	}
}

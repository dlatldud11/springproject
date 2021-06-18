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
public class MemberLoginController extends SuperClass{
	private final String command = "/login.me"; // 요청 커맨드
	private final String redirect = "redirect:/main.co";
	private ModelAndView mav = null;
	
	@Autowired
	@Qualifier("mdao")
	private MemberDao dao; 
	
	public MemberLoginController() {
		super("meLoginForm",null); // get, post 로 왔을 시 갈 jsp를 적어둠
		this.mav = new ModelAndView();
	}
	
	@GetMapping(command)
	public ModelAndView doGet() {
		this.mav.setViewName(super.getpage);
		return this.mav;
	}
	
	@PostMapping(command)
	public ModelAndView doPost(
			@RequestParam(value = "id", required = true) String id,
			@RequestParam(value = "password", required = true) String password,
			HttpSession session) {
		
		boolean isCheck = true; // false 이면 유효성 검사에 문제가 있습니다.
		
		if (id.length() <4 || id.length() > 10) {
			this.mav.addObject(super.PREFIX + "id","아이디는 4자리 이상 10자리 이하이어야 합니다.");
			isCheck = false;
		}
		if (password.length() <4 || password.length() > 10) {
			this.mav.addObject(super.PREFIX + "password","비밀번호는 4자리 이상 10자리 이하이어야 합니다.");
			isCheck = false;
		}
		if (isCheck == true) {
			Member bean = this.dao.SelectData(id, password);
			
			if(bean == null) { // failure
				String message = "아이디나 비번이 잘못 되었습니다.";
				this.mav.addObject("errmsg",message);
				this.mav.setViewName(super.getpage);
			}else { // success
				// 세션영역에 로그인 정보를 바인딩 합니다.
				session.setAttribute("loginfo", bean);
				// 장바구니 테이블에서 이전 나의 쇼핑정보 가져오기
				
				this.mav.setViewName(redirect);
			} 
		}else { // 문제가 있음
			this.mav.addObject("id",id);
			this.mav.addObject("password",password);
			this.mav.setViewName(super.getpage);
		}
		
		return this.mav;
	}
}

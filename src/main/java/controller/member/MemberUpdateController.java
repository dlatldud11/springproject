package controller.member;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import bean.CheckBean;
import bean.Member;
import controller.common.SuperClass;
import dao.MemberDao;
@Controller
public class MemberUpdateController extends SuperClass{
	private final String command = "/update.me";
	private ModelAndView mav = null;
	private final String redirect = "redirect:/main.co";
	
	@Autowired
	@Qualifier("mdao")
	private MemberDao mdao;
	
	public MemberUpdateController() {
		super("meUpdateForm","boList");
		this.mav = new ModelAndView();
	}
	@GetMapping(command)
	public ModelAndView doGet(
			@RequestParam(value = "id", required = true) String id) {
		Member bean = mdao.SelectDataByPk(id);
		this.mav.addObject("bean", bean);
		this.mav.setViewName(super.getpage);
		return this.mav;
	}
	
	@PostMapping(command)
	public ModelAndView doPost(
		@ModelAttribute("member") @Valid Member xxx,
		BindingResult asdf) {
		
		if (asdf.hasErrors()) { // failure
			System.out.println("유효성 검사에 문제가 있음");
			System.out.println(xxx.toString());
			System.out.println(asdf.toString());
			this.mav.addObject("bean",xxx);
			this.mav.setViewName(super.getpage);
			
		} else { // success
			System.out.println("유효성 검사에 문제가 없음");
			int cnt = -99999;
			cnt = mdao.UpdateData(xxx);
			this.mav.setViewName(redirect);
		}
		return this.mav;
	}
	
	 @ModelAttribute("member")
	   public Member mymember() {      
	      return new Member();
	   }
	   
	   @ModelAttribute("checklist")
	   public List<CheckBean> aaa() {   
	      List<CheckBean> lists = mdao.GetList("member", "hobby", "checkbox");
	      return lists ;
	   }
	   @ModelAttribute("radiolist")
	   public List<CheckBean> bbb() {   
	      List<CheckBean> lists = mdao.GetList("member", "gender", "radio");
	      return lists ;
	   }
	   @ModelAttribute("selectlist")
	   public List<CheckBean> ccc() {   
	      List<CheckBean> lists = mdao.GetList("member", "job", "select");
	      return lists ;
	   }
	   

	
}

package controller.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import controller.common.SuperClass;
import dao.MemberDao;
@Controller
public class TemplateController extends SuperClass{
	private final String command = "/수정01.me";
	private ModelAndView mav = null;
	private final String redirect = "redirect:/수정02.me";
	
	@Autowired
	@Qualifier("mdao")
	private MemberDao mdao;
	
	public TemplateController() {
		super("요기","저기");
		this.mav = new ModelAndView();
	}
	
	@GetMapping(command)
	public ModelAndView doGet() {
		return this.mav;
	}
	
	@PostMapping(command)
	public ModelAndView doPost() {
		return this.mav;
	}
	
}

package controller.mall;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import bean.Member;
import controller.common.SuperClass;
import dao.CompositeDao;

@Controller
public class TemplateController extends SuperClass {
	private final String command = "/수정01.mall";
	private ModelAndView mav = null;
	private final String redirect = "redirect:/수정02.mall";
	
	@Autowired
	@Qualifier("cdao")
	private CompositeDao dao;
	
	public TemplateController() {
		super(null, null);
		this.mav = new ModelAndView();
	}
	@GetMapping(command)
	public ModelAndView doGet() {
		return this.mav;
	}
	
	@PostMapping(command)
	public ModelAndView doPost(
			HttpSession session) {
		
		Member loginfo = (Member)session.getAttribute("loginfo");
		
		Member mycart = (Member)session.getAttribute("mycart");
		
		return this.mav;
	}
}

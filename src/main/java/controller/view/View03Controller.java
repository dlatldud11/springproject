package controller.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import bean.Combo03;
import controller.common.SuperClass;
import dao.CompositeDao;

@Controller
public class View03Controller extends SuperClass{
	private final String command = "/exam03.vw" ; 
	private ModelAndView mav = null ;
	private String redirect = "redirect:/exam03.vw" ;
	
	@Autowired
	@Qualifier("cdao")
	private CompositeDao dao ;
	
	public View03Controller() {
		super("View03", "View03");
		this.mav = new ModelAndView();
	}
	
	@GetMapping(command)
	public ModelAndView doGet(){
		List<Combo03> lists = dao.View03() ;
		
		this.mav.addObject("lists", lists);
		this.mav.setViewName(super.getpage);
		return this.mav ;
	}
}
package controller.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import bean.Combo01;
import controller.common.SuperClass;
import dao.CompositeDao;

@Controller
public class View01Controller extends SuperClass{
	private final String command = "/exam01.vw" ; 
	private ModelAndView mav = null ;
	private String redirect = "redirect:/exam01.vw" ;
	
	@Autowired
	@Qualifier("cdao")
	private CompositeDao dao ;
	
	public View01Controller() {
		super("View01", "View01");
		this.mav = new ModelAndView();
	}
	
	@GetMapping(command)
	public ModelAndView doGet(){
		List<Combo01> lists = dao.View01() ;		
		this.mav.addObject("lists", lists);
		this.mav.setViewName(super.getpage);
		return this.mav ;
	}
}
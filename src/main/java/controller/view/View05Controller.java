package controller.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import bean.Combo05;
import controller.common.SuperClass;
import dao.CompositeDao;

@Controller
public class View05Controller extends SuperClass{
	private final String command = "/exam05.vw" ; 
	private ModelAndView mav = null ;
	private String redirect = "redirect:/exam05.vw" ;
	
	@Autowired
	@Qualifier("cdao")
	private CompositeDao dao ;
	
	public View05Controller() {
		super("View05", "View05");
		this.mav = new ModelAndView();
	}
	
	@GetMapping(command)
	public ModelAndView doGet(){
		List<Combo05> lists = dao.View05();

		this.mav.addObject("lists", lists);
		
		this.mav.setViewName(super.getpage);
		return this.mav ;
	}
}
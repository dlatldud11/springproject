package controller.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import bean.Combo04;
import controller.common.SuperClass;
import dao.CompositeDao;

@Controller
public class View04Controller extends SuperClass {
	private final String command = "/exam04.vw";
	private ModelAndView mav = null;
	private String redirect = "redirect:/exam04.vw";

	@Autowired
	@Qualifier("cdao")
	private CompositeDao dao;

	public View04Controller() {
		super("View04", "View04");
		this.mav = new ModelAndView();
	}

	@GetMapping(command)
	public ModelAndView doGet() {
		List<Combo04> lists = dao.View04();

		this.mav.addObject("lists", lists);
		this.mav.setViewName(super.getpage);
		return this.mav;
	}
}
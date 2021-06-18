package controller.board ;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import controller.common.SuperClass;
import dao.BoardDao;

@Controller
public class BoardDeleteController extends SuperClass{
	private final String command = "/delete.bo" ; 
	private ModelAndView mav = null ;
	private String redirect = "redirect:/list.bo" ;
	
	@Autowired
	@Qualifier("bdao")
	private BoardDao dao ;
	
	public BoardDeleteController() { 
		super("boList", "boList");
		this.mav = new ModelAndView();
	}
	
	@GetMapping(command)
	public ModelAndView doGet(
			@RequestParam(value = "no", required = true) int no){
		int cnt = -999999 ;
		cnt = dao.DeleteData(no) ;
 		
		this.mav.setViewName(this.redirect);
		return this.mav ;
	}
}
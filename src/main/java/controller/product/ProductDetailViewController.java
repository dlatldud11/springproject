package controller.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import bean.Product;
import controller.common.SuperClass;
import dao.ProductDao;

@Controller
public class ProductDetailViewController extends SuperClass{
	private final String command = "/detailview.pr" ; 
	private ModelAndView mav = null ;
	private String redirect = "redirect:/list.pr" ;
	
	@Autowired
	@Qualifier("pdao")
	private ProductDao dao ;
	
	public ProductDetailViewController() {
		super("prDetailView", "prList");
		this.mav = new ModelAndView();
	}
	
	@GetMapping(command)
	public ModelAndView doGet(
			@RequestParam(value = "num", required = true) int num){
		Product bean  = dao.SelectDataByPk( num );
		
		if( bean != null){ //상세 보기로 이동			 
			mav.addObject("bean", bean);
			this.mav.setViewName(super.getpage);	
		}else{
			this.mav.setViewName(this.redirect);		 
		}		
		return this.mav ;
	}
}
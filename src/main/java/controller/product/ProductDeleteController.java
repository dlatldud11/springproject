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
public class ProductDeleteController extends SuperClass{
	private final String command = "/delete.pr" ; 
	private ModelAndView mav = null ;
	private String redirect = "redirect:/list.bo" ;
	
	@Autowired
	@Qualifier("pdao")
	private ProductDao dao ;
	
	public ProductDeleteController() {
		super("prList", "prList");
		this.mav = new ModelAndView();
	}
	
	@GetMapping(command)
	public ModelAndView doGet(
			@RequestParam(value = "num", required = true) int num){
		
		Product bean = dao.SelectDataByPk(num) ;
		int cnt = -999999 ;
		String remark = "상품 " + bean.getName() + "(" + num + ") 삭제됨";
		cnt = dao.DeleteData(num, remark) ;
	 	this.mav.setViewName(this.redirect);
		return this.mav ;
	}
}
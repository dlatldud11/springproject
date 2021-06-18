package controller.product;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import bean.Product;
import controller.common.SuperClass;
import dao.ProductDao;

@Controller
public class ProductInsertController extends SuperClass{
	private final String command = "/insert.pr" ; 
	private ModelAndView mav = null ;
	private String redirect = "redirect:/list.pr" ;
	
	@ModelAttribute("product")
	public Product myproduct() {
		System.out.println("@ModelAttribute(\"product\")");
		return new Product();
	}
	
	@Autowired
	@Qualifier("pdao")
	private ProductDao dao ;
	
	public ProductInsertController() {
		super("prInsertForm", "prList");
		this.mav = new ModelAndView();
	}
	
	@GetMapping(command)
	public ModelAndView doGet(){		
		this.mav.setViewName(super.getpage);
		System.out.println("doGet 메소드");
		return this.mav ;
	}
	
	@PostMapping(command)
	public ModelAndView doPost(
			@ModelAttribute("product") @Valid Product xxx,
			BindingResult asdf,
			HttpServletRequest request){
		
		if (asdf.hasErrors()) {
			System.out.println("유효성 검사에 문제 있슴");
			System.out.println(asdf);
			this.mav.addObject("bean", xxx);	
			this.mav.setViewName(super.getpage);
			
		} else {
			System.out.println("유효성 검사에 문제 없슴");
			MultipartFile multi = xxx.getAbcd() ;
			String uploadPath = "/upload" ;
			//realPath :  
			String realPath = request.getRealPath(uploadPath) ;
			System.out.println(realPath);			
			
			try {
				File destination = utility.Utility.getUploadedFileInfo(multi, realPath)  ;
				
				multi.transferTo(destination);
				mav.setViewName(this.redirect) ;				
				
				xxx.setImage(destination.getName());
				this.dao.InsertData(xxx);
				
			} catch (IllegalStateException e) {				
				e.printStackTrace();
				mav.setViewName("") ;
				
			} catch (Exception e) {				
				e.printStackTrace();
				mav.setViewName(this.redirect) ;
			}
		}			
		return this.mav ;
	}
}
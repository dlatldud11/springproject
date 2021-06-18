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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import bean.Product;
import controller.common.SuperClass;
import dao.ProductDao;

@Controller
public class ProductUpdateController extends SuperClass{
	private final String command = "/update.pr" ; 
	private ModelAndView mav = null ;
	private String redirect = "redirect:/list.pr" ;
	
	@ModelAttribute("product")
	public Product myproduct() {
		return new Product();
	}
	
	@Autowired
	@Qualifier("pdao")
	private ProductDao dao ;
	
	public ProductUpdateController() {
		super("prUpdateForm", "prList");
		this.mav = new ModelAndView();
	}
	
	@GetMapping(command)
	public ModelAndView doGet(
			@RequestParam(value = "num", required = true) int num){
		
		// 여기서 xxx는 현재 수정하고자 하는 이전에 기입했던 게시물 1건을 의미합니다.
		Product xxx = dao.SelectDataByPk(num);
		
		this.mav.addObject("bean", xxx);
				
		this.mav.setViewName(super.getpage);
		return this.mav ;
	}
	
	@PostMapping(command)
	public ModelAndView doPost(
			@ModelAttribute("product") @Valid Product xxx,
			BindingResult asdf,
			HttpServletRequest request,
			@RequestParam(value = "oldimage", required = false) String oldimage){
		
		System.out.println(this.getClass() + " doPost 메소드");
		System.out.println("빈 객체 정보");
		System.out.println(xxx.toString());
		System.out.println(oldimage); //지워야 할 이미지 정보
 		
		if ( asdf.hasErrors() ) {
			System.out.println("유효성 검사에 문제 있슴");
			System.out.println( asdf );
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
				String deleted_image = realPath + "/" + oldimage ;
				new File(deleted_image).delete();
				
				File destination = utility.Utility.getUploadedFileInfo(multi, realPath)  ;
				
				multi.transferTo(destination);				
				
				xxx.setImage(destination.getName());
				this.dao.UpdateData(xxx);
				
				mav.setViewName(this.redirect) ;				
				
			} catch (IllegalStateException e) {				
				e.printStackTrace();
				mav.setViewName(this.redirect) ;
				
			} catch (Exception e) {				
				e.printStackTrace();
				mav.setViewName(this.redirect) ;
			}
		}			
		return this.mav ;
	}
}
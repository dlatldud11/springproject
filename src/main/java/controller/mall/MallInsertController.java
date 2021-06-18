package controller.mall;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import bean.Member;
import controller.common.SuperClass;
import dao.CompositeDao;
import shopping.MyCartList;

@Controller
public class MallInsertController extends SuperClass {
	private final String command = "/insert.mall";
	private ModelAndView mav = null;
	private final String redirect = "redirect:/list.pr";
	
	@Autowired
	@Qualifier("cdao")
	private CompositeDao dao;
	
	public MallInsertController() {
		super(null, null);
		this.mav = new ModelAndView();
	}
	@GetMapping(command)
	public ModelAndView doGet() {
		return this.mav;
	}
	
	@PostMapping(command)
	public ModelAndView doPost(
			@RequestParam(value = "num", required = true)int num,
			@RequestParam(value = "stock", required = true)int stock,
			@RequestParam(value = "qty", required = true)int qty,
			HttpSession session) {
		
		Member loginfo = (Member)session.getAttribute("loginfo");
		
		if(loginfo == null) { //미로그인
			String message = "로그인이 필요합니다.";
			this.mav.addObject("errmsg",message);
			this.mav.setViewName("meLoginForm"); // go to login page
		}else { // 로그인 함
			// num : 상품 번호, stock : 재고 수량, qty: 구매할 수량
			if(stock < qty) { // 재고 수량 부족
				String message = "재고 수량이 부족합니다.";
//				this.mav.addObject("errmsg",message);
				session.setAttribute("errmsg", message);
				this.mav.setViewName(this.redirect);
				
			} else { //재고수량 충분
	            System.out.println("재고 충분");
	            MyCartList mycart = (MyCartList) session.getAttribute("mycart");
	            if(mycart == null) { //카트 준비가 안되었으면
	               mycart = new MyCartList();// 매장 입구에서 카트 준비
	            }
	            mycart.AddOrder(num, qty);
	            session.setAttribute("mycart", mycart);
	            
	            System.out.println("장바구니 품목 수");
	            System.out.println(mycart.GetAllOrderLists().size());
	            
	            this.mav.setViewName(this.redirect);// 한시적
	            //this.mav.setViewName("redirect:/list.mall");// 장바구니 목록 페이지로 이동

	         }
		}
		return this.mav;
	}
}

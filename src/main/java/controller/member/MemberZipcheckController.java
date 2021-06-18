package controller.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import bean.Postcode;
import controller.common.SuperClass;
import dao.CompositeDao;
import dao.MemberDao;

@Controller
public class MemberZipcheckController extends SuperClass{
   private final String command = "/zipcheck.me" ; 
   private ModelAndView mav = null ;
   
   @Autowired
   @Qualifier("cdao")
   private CompositeDao dao ;
   
   public MemberZipcheckController() {
      super("zipCheck", null);
      this.mav = new ModelAndView();
   }
   
   @GetMapping(command)
   public ModelAndView doGet(
         @RequestParam(value = "dong", required = false) String dong){
      
      List<Postcode> lists = null ;      
      
      if ( dong != null ) {
         lists = this.dao.SelectDataZipcode(dong) ;
         System.out.println("동네이름"+dong);
         System.out.println( "검색된 동네 개수 : " + lists.size() );
      }       
      
//      model.addAttribute( "lists", lists );
//      model.addAttribute( "dong", dong );
      
      this.mav.addObject("dong", dong);
      this.mav.addObject("lists", lists);

      this.mav.setViewName(super.getpage);
      return this.mav ;
   }
   @PostMapping(command)
   public ModelAndView doPost() {
      System.out.println("aaa");
      return null ;
   }
}
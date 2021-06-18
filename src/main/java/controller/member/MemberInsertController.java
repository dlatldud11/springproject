package controller.member;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import bean.CheckBean;
import bean.Member;
import controller.common.SuperClass;
import dao.MemberDao;

@Controller
public class MemberInsertController extends SuperClass{
   private final String command = "/insert.me" ; //수정01
   private ModelAndView mav = null ;
   private String redirect = "redirect:/login.me" ;   //수정02
   
   public MemberInsertController() {
      super("meInsertForm", "boList");
      this.mav = new ModelAndView();
   }
   
   @Autowired
   @Qualifier("mdao")
   private MemberDao mdao ;
   
   @ModelAttribute("member")
   public Member mymember() {      
      return new Member();
   }
   
   @ModelAttribute("checklist")
   public List<CheckBean> aaa() {   
      List<CheckBean> lists = mdao.GetList("member", "hobby", "checkbox");
      return lists ;
   }
   @ModelAttribute("radiolist")
   public List<CheckBean> bbb() {   
      List<CheckBean> lists = mdao.GetList("member", "gender", "radio");
      return lists ;
   }
   @ModelAttribute("selectlist")
   public List<CheckBean> ccc() {   
      List<CheckBean> lists = mdao.GetList("member", "job", "select");
      return lists ;
   }
   
   
   
   @GetMapping(command)
   public ModelAndView doGet(){      
      this.mav.setViewName(super.getpage);
      return this.mav ;
   }
   
   @PostMapping(command)
   public ModelAndView doPost(
         //커멘드 객체를 사용하여 유효성 검사를 수행해야합니다.
         @ModelAttribute("member") @Valid Member xxx,
         BindingResult asdf){
      
      if ( asdf.hasErrors() ) {
         System.out.println("유효성 검사에 문제 있슴");
         System.out.println(xxx);
         System.out.println(asdf);
         this.mav.addObject("bean", xxx);   
         this.mav.setViewName(super.getpage);
         System.out.println("e");
      } else {
         System.out.println("유효성 검사에 문제 없슴");
         int cnt = -99999 ;          
         //Bean 객체를 이용하여 해당 게시물을 추가한다.
         cnt = mdao.InsertData(xxx) ;         
         
         mav.setViewName(redirect);
         // request 객체의 내용을 보존하면서 목록 보기 페이지로 넘겨 줍니다.
         String parameter = "?id=" + xxx.getId() + "&password=" + xxx.getPassword() ;
         this.mav.setViewName(this.redirect + parameter);
         System.out.println("d");
      }         
      return this.mav ;
   }
}
package mypkg.comment;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import mypkg.bean.Comment;
import controller.common.SuperClass;
import mypkg.dao.CommentDao;


public class CommentListController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		int no = Integer.parseInt( request.getParameter("no") ) ; 
		System.out.println( this.getClass() + " no : " + no );
		CommentDao dao = new CommentDao() ;
		List<Comment> comments =  dao.SelectCommentListByPk( no ) ;
		System.out.println( "Comment 개수 : " + comments.size() );
		
		JSONArray jsArr = new JSONArray() ;
		
		for( Comment comm : comments){
			JSONObject jsObj = new JSONObject();
			jsObj.put("cnum", comm.getCnum() ) ;
			jsObj.put("writer", comm.getWriter() ) ;
			jsObj.put("content", comm.getContent() ) ;
			jsObj.put("regdate", comm.getRegdate() ) ;			
			jsArr.add( jsObj ) ;
		}
			
		model.addAttribute("jsArr", jsArr); 
		
		String gotopage = "/comment/comment.jsp" ;
		RequestDispatcher dispatcher
			= request.getRequestDispatcher( gotopage ) ;
		dispatcher.forward(request, response);	
	}
}

package mypkg.comment;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mypkg.board.BoardDetailViewController;
import controller.common.SuperClass;
import mypkg.dao.CommentDao; 

public class CommentDeleteController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		this.doPost(request, response);
	}
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		int cnum = Integer.parseInt( request.getParameter( "cnum" )) ;
		int no = Integer.parseInt( request.getParameter( "no" )) ;
		CommentDao dao = new CommentDao() ;
		int cnt = -99999;
		
		cnt = dao.DeleteCommentData( cnum ) ;
		
		new BoardDetailViewController().doGet(request, response);
	}
}
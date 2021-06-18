package mypkg.comment;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mypkg.bean.Comment;
import mypkg.board.BoardDetailViewController;
import controller.common.SuperClass;
import mypkg.dao.CommentDao;

public class CommentInsertController extends SuperClass{
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String writer = request.getParameter("writer") ;
		String content = request.getParameter("content") ;
		System.out.println( writer + "/" + content );
		
		Comment bean = new Comment() ;
		bean.setNo( Integer.parseInt( request.getParameter("no") ) );
		bean.setContent( content );
		bean.setWriter( writer );
		
		CommentDao dao = new CommentDao() ;
		int cnt = -999999 ;
		
		cnt = dao.InsertCommentData( bean ) ;
		
		new BoardDetailViewController().doGet(request, response);
	}
}
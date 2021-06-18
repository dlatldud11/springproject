package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Comment;

public class CommentDao {

	public List<Comment> SelectCommentListByPk(int no) {
		Connection conn = null ;
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;

		String sql = " select * from comments " ;
		sql += " where no = ? order by cnum " ;
		
		List<Comment> lists = new ArrayList<Comment>();
		try {
			conn = super.getConnection() ;	
			pstmt = conn.prepareStatement(sql) ;			
			pstmt.setInt(1, no);
		
			rs = pstmt.executeQuery() ;			
			while( rs.next() ){
				Comment bean = new Comment();

				bean.setCnum( Integer.parseInt( rs.getString("cnum") ));
				bean.setContent( rs.getString("content") );
				bean.setNo( Integer.parseInt( rs.getString("no") ));
				bean.setRegdate( String.valueOf( rs.getDate("regdate") ));
				bean.setWriter( rs.getString("writer") );
		  
				lists.add( bean ) ;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				if( rs != null ){ rs.close(); }
				if( pstmt != null ){ pstmt.close(); }
				if( conn != null){ conn.close(); } 
			} catch (Exception e2) {
				e2.printStackTrace(); 
			}
		}
		
		return lists ;
	}
	public int InsertCommentData(Comment bean) {		
		System.out.println( bean.toString() );
		
		Connection conn = null ;
		PreparedStatement pstmt = null ;
		
		String sql = " insert into comments(cnum, no, writer, content, regdate) " ;
		sql += " values(seqcomm.nextval, ?, ?, ?, sysdate ) " ;
		
		int cnt = -99999 ;
		try {
			conn = super.getConnection() ;	
			conn.setAutoCommit( false );
			pstmt = conn.prepareStatement(sql) ;
			
			pstmt.setInt(1, bean.getNo() );
			pstmt.setString(2, bean.getWriter() );
			pstmt.setString(3, bean.getContent() );
			
			cnt = pstmt.executeUpdate() ; 
			conn.commit(); 
		} catch (Exception e) {
			SQLException err = (SQLException)e ;			
			cnt = - err.getErrorCode() ;			
			e.printStackTrace();
			try {
				conn.rollback(); 
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} finally{
			try {
				if( pstmt != null ){ pstmt.close(); }
				if( conn != null ){ conn.close(); }
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return cnt ;
	}

	public int DeleteCommentData(int cnum) {
		Connection conn = null ;
		String sql = " delete from comments where cnum = ? " ;
		PreparedStatement pstmt = null ;
		int cnt = -99999 ;
		try {
			conn = super.getConnection() ; 
			conn.setAutoCommit( false );
			pstmt = conn.prepareStatement(sql) ;
			pstmt.setInt(1, cnum);
			
			cnt = pstmt.executeUpdate() ; 
			conn.commit(); 
		} catch (Exception e) {
			SQLException err = (SQLException)e ;			
			cnt = - err.getErrorCode() ;			
			e.printStackTrace();
			try {
				conn.rollback(); 
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} finally{
			try {
				if( pstmt != null ){ pstmt.close(); }
				if( conn != null ){ conn.close(); }
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return cnt ;
	}
}
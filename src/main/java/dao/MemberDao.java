package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bean.CheckBean;
import bean.Member;
import shopping.ShoppingInfo;

@Component("mdao")
public class MemberDao {
	private final String namespace = "MapperMember." ;	
	
	@Autowired
	SqlSessionTemplate abcd;
	
	public MemberDao() { }	
	
	//아이디와 비번을 사용하여 해당 회원이 존재하나요?
	public Member SelectData(String id, String password) {
		Map<String, String> map = new HashMap<String, String>() ;
		map.put("id", id) ;
		map.put("password", password) ;
		return this.abcd.selectOne(namespace + "SelectData", map);
	}	

	public Member SelectDataByPk(String id) {
		// 아이디 정보를 이용하여 회원을 찾아 줍니다.
		return this.abcd.selectOne(namespace + "SelectDataByPk", id);
	}
	
	public int InsertData(Member bean) {
		return this.abcd.insert(namespace + "InsertData", bean);
	}	
	
	public List<CheckBean> GetList(String module, String field, String kind) {		
		// 체크 박스, 라디오 버튼, 콤보 박스의 내용들을 가져옵니다.
		Map<String, String> map = new HashMap<String, String>() ;
		map.put("module", module) ;
		map.put("field", field) ;
		map.put("kind", kind) ;
		return this.abcd.selectList(namespace + "GetList", map);	
	}	
	
	public int UpdateData(Member bean) {
		// 회원 정보를 수정합니다.
		return this.abcd.update(namespace + "UpdateData", bean);
	}

	public List<Member> SelectDataList(int offset, int limit) {
		// RowBounds 객체를 사용한 페이징 처리입니다.
		RowBounds rbs = new RowBounds(offset, limit);
		return this.abcd.selectList(namespace + "SelectDataList", null, rbs); //null 은 파라미터
	}

	public int SelectTotalCount() {
		return this.abcd.selectOne(namespace + "SelectTotalCount");
	}

	public int DeleteData(Member bean) {
		// boards.remark 수정, orders.remark 수정
		// 해당 id를 이용하여 회원 탈퇴를 수행합니다.		
						
		// 탈퇴할 회원이 남긴 게시물 정보의 remark 컬럼 정보를 수정합니다.
		Map<String, String> map = new HashMap<String, String>() ;
		
		// 심형래(sim09)가 회원 탈퇴를 하였습니다.
		String remark = bean.getName() + "(" + bean.getId() + ")가 회원 탈퇴를 하였습니다." ;
		
		map.put("writer", bean.getId()) ;		
		
		map.put("remark", remark) ;
		
		this.abcd.update(namespace + "UpdateBoardRemark", map);			
		
		// orders.remark 수정 그래서 member 통채로 넘겨줌
		map.clear();
		map.put("remark", remark) ;
		map.put("mid", bean.getId()) ;
		this.abcd.update(namespace + "UpdateOrderRemark", map);

		// 회원 탈퇴하기			
		return this.abcd.delete(namespace + "DeleteData", bean.getId());		
	}
	public void InsertCartData(Member mem, List<ShoppingInfo> lists) {
		// 1. 장바구니 테이블에 혹시 남아 있을 수 있는 회원의 행을 모두 삭제합니다. 
		this.abcd.delete(namespace + "DeleteShoppingInfo", mem.getId());
		
		// 2.반복문을 사용하여 테이블에 인서트 합니다.
		for(ShoppingInfo shpInfo : lists){
			this.abcd.insert(namespace + "InsertShoppingInfo", shpInfo);
		}
	}
}
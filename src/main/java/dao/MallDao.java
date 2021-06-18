package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bean.Member;
import bean.Order;
import bean.OrderDetail;
import bean.Product;
import shopping.ShoppingInfo;

@Component("malldao")
public class MallDao {
	private final String namespace = "MapperMall." ;	
	
	@Autowired
	SqlSessionTemplate abcd;
	
	public MallDao() { }
	
	public Order SelectDataByPk(int oid) {
		return this.abcd.selectOne(namespace + "SelectDataByPk", oid);
	}
	
	public void Calculate(Member mem, Map<Integer, Integer> maplists, int totalPoint) {
		// mem 객체는 고객 정보이고, maplists 객체는 구매한 상품 리스트 입니다
		// totalPoint는 회원에게 적립할 마일리지 포인트 금액입니다.
		
		// 고객의 장바구니에 대한 결재를 진행합니다.
		// 1. 주문(orders) 테이블에 추가합니다.		
		Order order = new Order();
		order.setMid(mem.getId());
		order.setRemark(" ");
		this.abcd.insert(namespace + "InsertOrder", order);
					
		// 2. 방금 추가된 송장 번호를 읽어 옵니다.
		int maxnum = -100 ;
		maxnum = this.abcd.selectOne(namespace + "GetMaxNum");		
		System.out.println("신규 송장 번호 : " + maxnum); 		
		Set<Integer> keylist = maplists.keySet() ;
		System.out.println("상품 개수 : " + keylist.size());
		
		Map<String, Object> map = new HashMap<String, Object>() ;
			
		// 반복문을 사용하여 
		for(Integer pnum : keylist){
			// 3. 주문 상세(orderdetails) 테이블에 추가합니다.
			
			OrderDetail od = new OrderDetail() ;
			
			// orders의 oid와 orderdetails의 oid는 동일한 값입니다.
			// 송장 번호가 참조 무결성 제약 조건에 의하여 연결이 되어 있습니다.
			od.setOid(maxnum); // 신규로 생성된 송장 번호
			od.setPnum(pnum); // 해당 상품 번호
			int qty = maplists.get(pnum) ;
			od.setQty(qty); // 구매한 수량
			od.setRemark(" ");
			this.abcd.insert(namespace + "InsertOrderDetail", od);
			
			// 4. 해당 상품 번호(pnum)를 이용하여 재고 수량(stock)을 감소시킵니다.
			map.put("stock", qty) ;
			map.put("num", pnum) ;
			this.abcd.update(namespace + "UpdateStock", map);
		}

		map.clear();
		
		// 5. 구매자에 대한 마일리지 적립 포인트를 누적시켜 줍니다.
		map.put("mpoint", totalPoint) ;
		map.put("id", mem.getId()) ;
		this.abcd.update(namespace + "UpdatePoint", map);			
	}
	
	public List<Order> OrderMall(String id) {
		return this.abcd.selectList(namespace + "OrderMall", id);		
	}	
	
	public List<ShoppingInfo> ShowDetail(int oid) {
		return this.abcd.selectList(namespace + "ShowDetail", oid);				
	}	
	

	public List<ShoppingInfo> GetShoppingInfo(String id) {
//		String sql = " select * from shoppinginfos " ;
//		sql += " where mid = ? " ;
		return null ;
	}	
	

}

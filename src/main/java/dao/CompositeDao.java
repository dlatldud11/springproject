package dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bean.Combo01;
import bean.Combo02;
import bean.Combo03;
import bean.Combo04;
import bean.Combo05;
import bean.Postcode;
import shopping.ShoppingInfo;

@Component("cdao")
public class CompositeDao {
	private final String namespace = "MapperComposite" ;	
	
	@Autowired
	SqlSessionTemplate abcd;
	
	public CompositeDao() { }

	public List<Postcode> SelectDataZipcode(String dong) {
		dong = "%" + dong + "%";
		return this.abcd.selectList(namespace + ".SelectDataByPk", dong);		
	}
	
	public List<Combo05> View05() {	
		return this.abcd.selectList(namespace + ".View05");
	}
	public List<Combo04> View04() {
		return this.abcd.selectList(namespace + ".View04");
	}
	public List<Combo03> View03() {
		return this.abcd.selectList(namespace + ".View03");
	}
	public List<Combo02> View02() {
		return this.abcd.selectList(namespace + ".View02");
	}	
	public List<Combo01> View01() {
		return this.abcd.selectList(namespace + ".View01");
	}		
}
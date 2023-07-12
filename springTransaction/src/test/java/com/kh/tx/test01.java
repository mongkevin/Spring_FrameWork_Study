package com.kh.tx;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.kh.tx.model.vo.Category;
import com.kh.tx.model.vo.Product;


@RunWith(SpringJUnit4ClassRunner.class) //spring과 junit4를 연동하는 설정
@ContextConfiguration(locations = {     //설정파일의 위치경로를 알려줘야함(file: 프로젝트부터 시작경로)
		"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
@WebAppConfiguration //웹과 관련된 설정 무시
public class test01 {

	@Autowired
	private SqlSession sqlSession;
	
	@Test
	public void test01(){
		Category category = Category.builder().categoryNo(1).categoryName("과자").build();
		Product product = Product.builder().productNo(1).productName("눈을감자").categoryNo(1).build();
	
		sqlSession.insert("testMapper.insertCategory",category);
		sqlSession.insert("testMapper.insertProduct",product);
	}
}

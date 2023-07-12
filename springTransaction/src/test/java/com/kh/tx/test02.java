package com.kh.tx;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.kh.tx.model.service.TestService;
import com.kh.tx.model.vo.Category;
import com.kh.tx.model.vo.Product;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
@WebAppConfiguration
public class test02 {
	
	@Autowired
	private TestService testService;
	
	@Test
	public void test02() {
		
		//카테고리
		//상품
		Category category = Category.builder().categoryNo(3).categoryName("과자").build();
		Product product = Product.builder().productName("눈을감자").productNo(1).categoryNo(3).build();
		
		testService.add(category, product);
	}
}

package com.kh.tx.model.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.tx.model.vo.Category;
import com.kh.tx.model.vo.Product;

@Service
public class TestServiceImpl implements TestService{

	@Autowired
	private SqlSession sqlSession;

	@Override
	@Transactional //메소드는 단위 작업으로 처리한다.
	public void add(Category category, Product product) {
		
		
		sqlSession.insert("testMapper.insertCategory",category);
		sqlSession.insert("testMapper.insertProduct",product);
	}

}

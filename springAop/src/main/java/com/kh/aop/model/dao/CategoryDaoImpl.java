package com.kh.aop.model.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.aop.model.vo.Category;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class CategoryDaoImpl implements CategoryDao{

	@Autowired
	public SqlSession sqlSession;
	
	@Override
	public int insertCategory(Category category) {
		int result = sqlSession.insert("testMapper.insertCategory",category);
		
		return result;
	}

	@Override
	public ArrayList<Category> selectCategory() {
		//걸리는 시간을 측정을 이렇게 하면 귀찮다.
//		long start = System.currentTimeMillis();
		ArrayList<Category> list = (ArrayList)sqlSession.selectList("testMapper.selectCategory");
//		long finish = System.currentTimeMillis();
//		log.debug("time={}",finish-start);
		return list;
	}

	
}

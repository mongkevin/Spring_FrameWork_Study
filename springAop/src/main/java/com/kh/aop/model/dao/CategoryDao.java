package com.kh.aop.model.dao;

import java.util.ArrayList;

import com.kh.aop.model.vo.Category;

public interface CategoryDao {

	int insertCategory(Category category);
	ArrayList<Category> selectCategory();
}

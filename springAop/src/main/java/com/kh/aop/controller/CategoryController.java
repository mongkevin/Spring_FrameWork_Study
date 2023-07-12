package com.kh.aop.controller;



import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.aop.model.dao.CategoryDao;
import com.kh.aop.model.vo.Category;

@Controller
@RequestMapping("/category")
public class CategoryController {

//	@RequestMapping("list.bo")
//	public void test() {
//		
//	}
	
	@Autowired
	private CategoryDao categoryDao;
	
	@GetMapping("/select")
	public String slectCategory(Model model) {
		ArrayList<Category> list = categoryDao.selectCategory();
		model.addAttribute("list",list);
		return "category/list";
	}
	
	@PostMapping("/insert")
	public String insertCategory(Category category) {
		
		int result = categoryDao.insertCategory(category);
		
		return "redirect:select";
	}
	
	@GetMapping("/insert")
	public String insertCategory(Model model) {
		
		return "category/insert";
	}
}

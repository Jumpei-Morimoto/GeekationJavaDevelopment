package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Category;
import com.example.demo.repository.CategoryRepository;


@Service

public class CategoryListServiceImpl implements CategoryListService{
	@Autowired
	  private CategoryRepository categoryRepository;
	
	public List<Category> searchAll() {
	    return categoryRepository.findAll();
	}
	
	public Category findById(long id) {
		return categoryRepository.findById(id).get();
	}

}

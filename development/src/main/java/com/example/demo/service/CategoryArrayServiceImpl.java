package com.example.demo.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Sub_Category_reference;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.SubCategoryReferenceRepository;
import com.example.demo.repository.SubCategoryRepository;

@Service

public class CategoryArrayServiceImpl implements CategoryArrayService{
	
	@Autowired
	  private CategoryRepository categoryRepository;
	@Autowired
	  private SubCategoryRepository subcategoryRepository;
	@Autowired
	  private SubCategoryReferenceRepository subcategoryreferenceRepository;
	
	@Override
	public List<Sub_Category_reference> searchAll() {
	    return subcategoryreferenceRepository.findAll();
	}
	/*@Override
	public List<Sub_Category> searchAll() {
	    return subcategoryRepository.findAll();
	}*/
	@Override
	public Sub_Category_reference findById(long id) {
		return subcategoryreferenceRepository.findById(id).get();
	}
	
	

}

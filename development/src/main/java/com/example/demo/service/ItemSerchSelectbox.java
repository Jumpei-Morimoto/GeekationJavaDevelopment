package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Category;
import com.example.demo.entity.Sub_Category;
import com.example.demo.entity.Sub_SubCategory;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.SubCategoryRepository;
import com.example.demo.repository.SubSubCategoryRepository;


@Service
public class ItemSerchSelectbox {
	
	   @Autowired
	    private CategoryRepository categoryRepository;
	    @Autowired
	    private SubCategoryRepository subCategoryRepository;
	    @Autowired
	    private SubSubCategoryRepository subSubCategoryRepository;

	    public List<Category> searchAll() {
	        return categoryRepository.findAll();
	    }
	    public Category findById(long id) {
 		return categoryRepository.findById(id).get();
 	}
	    
	    public List<Sub_Category> searchAll2() {
	        return subCategoryRepository.findAll();
	    }
	    public Sub_Category findById2(long id) {
 		return subCategoryRepository.findById(id).get();
 	}
	    
	    public List<Sub_SubCategory> searchAll3() {
	        return subSubCategoryRepository.findAll();
	    }
	    
	    public Sub_SubCategory findById3(long id) {
 		return subSubCategoryRepository.findById(id).get();
 	}

}

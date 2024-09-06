package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Sub_SubCategoryReference;
import com.example.demo.repository.SubSubCategoryReferenceRepository;

@Service
public class SubCategoryArrayServiceImpl implements SubCategoryArrayService{
	
	@Autowired
	  private SubSubCategoryReferenceRepository subSubCategoryReferenceRepository;
	
	@Override
	public List<Sub_SubCategoryReference> searchAll() {
	    return subSubCategoryReferenceRepository.findAll();
	}

	@Override
	public Sub_SubCategoryReference findById(long id) {
		return subSubCategoryReferenceRepository.findById(id).get();
	}

}

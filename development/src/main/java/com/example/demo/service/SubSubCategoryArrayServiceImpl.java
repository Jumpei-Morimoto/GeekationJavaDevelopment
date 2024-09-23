package com.example.demo.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.ItemsReference;
import com.example.demo.repository.ItemsReferenceRepository;

@Service

public class SubSubCategoryArrayServiceImpl implements SubSubCategoryArrayService{

	@Autowired
	  private ItemsReferenceRepository itemsReferenceRepository;
	
	@Override
	public List<ItemsReference> searchAll() {
	    return itemsReferenceRepository.findAll();
	}

	@Override
	public ItemsReference findById(long id) {
		return itemsReferenceRepository.findById(id).get();
	}
}

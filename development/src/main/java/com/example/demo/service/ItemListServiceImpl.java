package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Items;
import com.example.demo.entity.ItemsReference;
import com.example.demo.repository.ItemsReferenceRepository;
import com.example.demo.repository.ItemsRepository;


@Service

public class ItemListServiceImpl implements ItemListService{
	
	
	@Autowired
	  private ItemsRepository itemRepository;
	
	@Autowired
	  private ItemsReferenceRepository itemsReferenceRepository;
	
	
	@Override
	public List<Items> searchAll() {
	    return itemRepository.findAll();
	}
	
	@Override
	public ItemsReference findById(long id) {
		return itemsReferenceRepository.findById(id).get();
	}

}

package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Items;
import com.example.demo.repository.ItemsRepository;


@Service
public class ItemListServiceImpl2 implements ItemListService2{
	
	
	@Autowired
	  private ItemsRepository itemsRepository;
	
	
	
	
	@Override
	public List<Items> searchAll() {
	    return itemsRepository.findAll();
	}
	
	@Override
	public Items findById(long id) {
		return itemsRepository.findById(id).get();
	}

}

package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Inventory;
import com.example.demo.repository.InventoryRepository;

@Service

public class InventoryListServiceImpl implements InventoryListService{
	
	@Autowired
	  private InventoryRepository inventoryRepository;
	
	
	
	@Override
	public List<Inventory> searchAll() {
	    return inventoryRepository.findAll();
	}
	
	@Override
	public Inventory findById(long id) {
		return inventoryRepository.findById(id)
				.orElse(null);
	}

	public Inventory findByStoreId(Long storeName) {
		return inventoryRepository.findByStoreId(storeName)
				.orElse(null);
	}


}

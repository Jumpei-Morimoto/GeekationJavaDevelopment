package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Items;
import com.example.demo.repository.ItemsRepository;

@Service
public class ItemRegistrationService{

	
	@Autowired
    private ItemsRepository itemsRepository;
	
      
    public Items saveItems(Items items) {
            return itemsRepository.save(items);
        }

	
}

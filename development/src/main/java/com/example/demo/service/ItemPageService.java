package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Items;
import com.example.demo.repository.ItemPageRepository;

@Service
public class ItemPageService {
	
	
	@Autowired 
    private ItemPageRepository itemPageRepository;

    public Page<Items> getItems(Pageable pageable) {
        return itemPageRepository.findAll(pageable);
    }

}

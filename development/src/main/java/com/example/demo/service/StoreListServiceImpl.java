package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Store;
import com.example.demo.repository.StoreRepository;

@Service
public class StoreListServiceImpl implements StoreListService{
	
	@Autowired
	  private StoreRepository storeRepository;
	
	@Override
	public List<Store> searchAll() {
	    return storeRepository.findAll();
	}
	@Override
	public Store findById(long id) {
		return storeRepository.findById(id).get();
	}

}

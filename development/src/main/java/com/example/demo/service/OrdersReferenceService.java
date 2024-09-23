package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.OrdersReference;
import com.example.demo.repository.OrdersReferenceRepository;

@Service
public class OrdersReferenceService {
	
	@Autowired
	  private OrdersReferenceRepository ordersRefereceRepository;
	
	public List<OrdersReference> searchAll() {
	    return ordersRefereceRepository.findAll();
	}
	
	public OrdersReference findById(long id) {
		return ordersRefereceRepository.findById(id).get();
	}

	public List<OrdersReference>  findByStoreId(Long storeId) {
		return ordersRefereceRepository.findByStoreId(storeId);
	}

}

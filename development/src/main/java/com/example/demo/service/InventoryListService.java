package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Inventory;

public interface InventoryListService {
	List<Inventory> searchAll();

	Inventory findById(long id);

}

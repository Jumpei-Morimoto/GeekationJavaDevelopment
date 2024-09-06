package com.example.demo.service;


import java.util.List;

import com.example.demo.entity.Items;

public interface ItemListService2 {
	

	List<Items> searchAll();

	Items findById(long id);

}

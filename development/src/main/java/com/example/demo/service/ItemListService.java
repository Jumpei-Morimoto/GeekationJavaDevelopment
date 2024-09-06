package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Items;
import com.example.demo.entity.ItemsReference;

public interface ItemListService {

	List<Items> searchAll();

	ItemsReference findById(long id);

}

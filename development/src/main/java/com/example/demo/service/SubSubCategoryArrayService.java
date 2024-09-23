package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.ItemsReference;

public interface SubSubCategoryArrayService {

	ItemsReference findById(long id);

	List<ItemsReference> searchAll();

}

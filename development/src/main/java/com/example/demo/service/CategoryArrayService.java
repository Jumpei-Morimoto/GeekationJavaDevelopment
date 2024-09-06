package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Sub_Category_reference;

public interface CategoryArrayService {

	List<Sub_Category_reference> searchAll();

	Sub_Category_reference findById(long id);

}

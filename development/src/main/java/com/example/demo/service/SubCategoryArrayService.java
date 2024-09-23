package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Sub_SubCategoryReference;

public interface SubCategoryArrayService {

	List<Sub_SubCategoryReference> searchAll();

	Sub_SubCategoryReference findById(long id);

}

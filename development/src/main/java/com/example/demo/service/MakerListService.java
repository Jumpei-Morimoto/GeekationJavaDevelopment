package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Maker;

public interface MakerListService {

	List<Maker> searchAll();

	Maker findById(long id);

}

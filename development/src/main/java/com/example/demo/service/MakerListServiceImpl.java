package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Maker;
import com.example.demo.repository.MakerRepository;


@Service

public class MakerListServiceImpl implements MakerListService{
	
	@Autowired
	  private MakerRepository makerRepository;
	
	@Override
	public List<Maker> searchAll() {
	    return makerRepository.findAll();
	}
	
	@Override
	public Maker findById(long id) {
		return makerRepository.findById(id).get();
	}

}

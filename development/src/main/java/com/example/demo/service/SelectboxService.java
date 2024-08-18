package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Positions;
import com.example.demo.entity.Roles;
import com.example.demo.entity.Store;
import com.example.demo.repository.PositionRepository;
import com.example.demo.repository.RolesRepository;
import com.example.demo.repository.StoreRepository;

@Service
public class SelectboxService {
	
	    @Autowired
	    private StoreRepository storeRepository;
	    @Autowired
	    private PositionRepository positionRepository;
	    @Autowired
	    private RolesRepository rolesRepository;

	    public List<Store> searchAll() {
	        return storeRepository.findAll();
	    }
	    public Store findById(long id) {
    		return storeRepository.findById(id).get();
    	}
	    
	    public List<Positions> searchAll2() {
	        return positionRepository.findAll();
	    }
	    public Positions findById2(long id) {
    		return positionRepository.findById(id).get();
    	}
	    
	    public List<Roles> searchAll3() {
	        return rolesRepository.findAll();
	    }
	    public Roles findById3(long id) {
    		return rolesRepository.findById(id).get();
    	}

}

package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Users_reference;
import com.example.demo.repository.UsersReferenceRepository;

@Service
public class UsersReferenceService {
	
	@Autowired
	  private UsersReferenceRepository usersRefereceRepository;
	
	public List<Users_reference> searchAll() {
	    return usersRefereceRepository.findAll();
	}
	
	public Users_reference findById(long id) {
		return usersRefereceRepository.findById(id).get();
	}

}

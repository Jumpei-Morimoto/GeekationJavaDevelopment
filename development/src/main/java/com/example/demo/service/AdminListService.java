package com.example.demo.service;
/*管理者一覧表示用*/


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Users;
import com.example.demo.repository.UsersRepository;


@Service
public class AdminListService {
	
	
	@Autowired
	  private UsersRepository usersRepository;
	
	public List<Users> searchAll() {
	    return usersRepository.findAll();
	}
	
	public Users findById(long id) {
		return usersRepository.findById(id).get();
	}
	
}

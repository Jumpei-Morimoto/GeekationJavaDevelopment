package com.example.demo.service;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Users;
import com.example.demo.form.AdministratorRegistrationForm;
import com.example.demo.repository.UsersRepository;


@Service
@Transactional(rollbackFor = Exception.class)
public class EditService {
	
	
	@Autowired
	private UsersRepository usersRepository2;
	
	public List<Users> searchAll(){
		return usersRepository2.findAll();
	}
	
	public Users findById(long id) {
		return usersRepository2.findById(id).get();
	}
	
	public void update3(AdministratorRegistrationForm usersUpdate3) {
		
		Users users4 = findById(usersUpdate3.getId());
		
		 Long userID4 =  Long.parseLong(usersUpdate3.getStoreName());
		 Long positionID4 =  Long.parseLong(usersUpdate3.getPositionsType());
	     Long roleID4 =  Long.parseLong(usersUpdate3.getRoleType());
		
		users4.setId(usersUpdate3.getId());
		users4.setStoreName(userID4);
		users4.setLastName(usersUpdate3.getLastName());
		users4.setFirstName(usersUpdate3.getFirstName());
		users4.setEmail(usersUpdate3.getEmail());
		users4.setPositionId(positionID4);
		users4.setRoleId(roleID4);
		users4.setPhone(usersUpdate3.getPhone());
		users4.setPassword(usersUpdate3.getPassword());
		users4.setUpdatedAt(new Date());
		usersRepository2.save(users4);
		
	}
	
	
	 public void deleteUsers(Long id) {
	        usersRepository2.deleteById(id);
	    }

}

package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Users;
import com.example.demo.form.AdministratorRegistrationForm;
import com.example.demo.repository.StoreRepository;
import com.example.demo.repository.UsersRepository;

@Service
public class AdministratorRegistrationServiceImpl implements AdministratorRegistrationService{
	
	@Autowired
    private UsersRepository usersRepository;
	
	@Autowired
    private StoreRepository storeRepository;
  
	@Override
	public void saveAdmin(AdministratorRegistrationForm administratorRegistrationForm) {
		Users users = new Users();
		
		Long userID =  Long.parseLong(administratorRegistrationForm.getStoreName());
		Long positionID =  Long.parseLong(administratorRegistrationForm.getPositionsType());
		Long roleID =  Long.parseLong(administratorRegistrationForm.getRoleType());
        
		users.setStoreName(userID);
		users.setLastName(administratorRegistrationForm.getLastName());
		users.setFirstName(administratorRegistrationForm.getFirstName());
		users.setEmail(administratorRegistrationForm.getEmail());
		users.setPositionId(positionID);
		users.setRoleId(roleID);
		users.setPhone(administratorRegistrationForm.getPhone());
		users.setPassword(administratorRegistrationForm.getPassword());

      
      usersRepository.save(users);

	}
	
}

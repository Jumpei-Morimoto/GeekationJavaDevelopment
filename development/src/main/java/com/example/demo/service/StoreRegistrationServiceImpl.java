package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Store;
import com.example.demo.form.StoreRegistrationForm;
import com.example.demo.repository.StoreRepository;

@Service
public class StoreRegistrationServiceImpl implements StoreRegistrationService{

	
	@Autowired
    private StoreRepository storeRepository;
  
	@Override
	public void saveStore(StoreRegistrationForm storeRegistrationForm) {
		Store stores = new Store();
		
	
		stores.setStoreName(storeRegistrationForm.getStoreName());
		stores.setAddress(storeRegistrationForm.getAddress());
		

      
      storeRepository.save(stores);

	}
}

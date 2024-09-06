package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Maker;
import com.example.demo.form.MakerRegistrationForm;
import com.example.demo.repository.MakerRepository;

@Service
public class MakerRegistrationServiceImpl implements MakerRegistrationService{
	
	@Autowired
	private MakerRepository makerRepository;
	
	@Override
	public void saveMaker(MakerRegistrationForm makerRegistrationForm) {
		Maker maker = new Maker();
        
		maker.setMakerName(makerRegistrationForm.getMakerName());
		
        makerRepository.save(maker);
	}
}

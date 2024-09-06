package com.example.demo.form;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MakerRegistrationForm implements Serializable{
	
	private Long id;

	@NotBlank
	private String makerName;
	
	
	private String createdAt;
	
	
    private String updatedAt;
    	
	
}

package com.example.demo.form;

import java.io.Serializable;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data

public class UserUpdateForm implements Serializable{
	
	@NotNull
	private Long id;

	@NotEmpty
    private Long storeName;

	@NotBlank
	private String lastName;
    
	@NotBlank
	private String firstName;
    
	@NotBlank
	@Email
	private String email;
	
	@NotEmpty
    private Long positionsType;
    
	@NotEmpty
    private Long roleType;
	
	@NotBlank
	@Size(min = 10, max = 11)
    private String phone;
	
	@NotBlank
	private String password;
	
}

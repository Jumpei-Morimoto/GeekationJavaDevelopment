package com.example.demo.form;
import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class StoreRegistrationForm implements Serializable{
	
	private Long id;

	@NotBlank(message = "空白は許可されていません")
	private String storeName;
	
	@NotBlank
	private String address;
	
    private String createdAt;
		
    private String updatedAt;
	
}

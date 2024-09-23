package com.example.demo.form;
import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ItemRegistrationForm implements Serializable{
	
	private Long id;

	@NotBlank
	private String itemName;
	
	@NotBlank
	private String itemBody;
	
	@NotBlank
	private String makerId;
	
	private MultipartFile file;
	
	@NotBlank
	private String purchase;
	
	@NotBlank
	private String listPrice;
	
	@NotBlank
	private String categoryId;
	
	@NotBlank
	private String subCategoryId;
	
	@NotBlank
	private String subSubCategoryId;
	
    private String createdAt;
    
		
    private String updatedAt;
    
    
	private String itemImage;
	
	
	private Long price;
	
}

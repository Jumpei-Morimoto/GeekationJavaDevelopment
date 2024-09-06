package com.example.demo.form;
import java.io.Serializable;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ItemUpdateForm implements Serializable{
	
	private Long id;

	@NotBlank
	private String itemName;
	
	@NotBlank
	private String itemBody;
	
	@NotBlank
	private Long makerId;
	
	private MultipartFile file;
	
	@NotBlank
	private Long purchase;
	
	@NotBlank
	private Long listPrice;
	
	@NotBlank
	private Long categoryId;
	
	@NotBlank
	private Long subCategoryId;
	
	@NotBlank
	private Long subSubCategoryId;
	
    private Date createdAt;
    
		
    private Date updatedAt;
    
   
	private String itemImage;
	
}

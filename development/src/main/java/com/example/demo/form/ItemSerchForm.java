package com.example.demo.form;

import java.io.Serializable;

import lombok.Data;

@Data
public class ItemSerchForm implements Serializable{
	
	private String itemName;

	
	private String categoryId;
	
	
	private String subCategoryId;
	
	
	private String subSubCategoryId;

}

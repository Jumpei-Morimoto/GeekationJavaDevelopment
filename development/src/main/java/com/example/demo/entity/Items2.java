package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data

public class Items2 {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "item_name",nullable = false)
	private String itemName;
	
	@Column(name = "item_body",nullable = false)
	private String itemBody;
	
	@Column(name = "purchase")
	private Long purchase;
	
	@Column(name = "list_price")
	private Long listPrice;
	
	
	private String category;
	
	
	private String subCategory;
	
	
	private String subSubCategory;

	

}

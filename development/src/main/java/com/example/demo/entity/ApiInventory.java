package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data

public class ApiInventory {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Long Id;
	
	@Column(name = "store_id")
	private Long storeId;
	
	@Column(name = "item_id",nullable = false)
	private String itemName;
	
	@Column(name = "quantity",nullable = false)
	private Long quantity;
	
	@Column(name = "Price",nullable = false)
	private Long price;
	

}

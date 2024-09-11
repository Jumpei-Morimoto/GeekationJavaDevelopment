package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class ApiOrders {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(nullable = false)
	private String userName;
	
	@Column(nullable = false)
	private String itemName;
	
	@Column(name = "purchase",nullable = false)
	private Long purchase;

	@Column(name = "store_id",nullable = false)
	private String storeName;
	
	@Column(name = "ordering_quantity",nullable = false)
	private Long orderingQuantity;
	
	@Column(name = "total",nullable = false)
	private Long total;

}

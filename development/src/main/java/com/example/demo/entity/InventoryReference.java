package com.example.demo.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "inventory")

public class InventoryReference {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "store_id")
	private Long storeId;
	
	@Column(name = "item_id",nullable = false)
	private Long itemId;
	
	@Column(name = "quantity",nullable = false)
	private String Quantity;
	
	@Column(name = "Price",nullable = false)
	private Long price;
	
	@Column(name = "created_at")
	  private Date createdAt;
	
	@Column(name = "updated_at")
	private Date updatedAt;

}

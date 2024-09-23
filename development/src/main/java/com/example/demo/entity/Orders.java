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
@Table(name = "orders")
public class Orders {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "user_id",nullable = false)
	private Long userId;
	
	@Column(name = "item_id",nullable = false)
	private Long itemId;
	
	@Column(name = "purchase",nullable = false)
	private Long purchase;
	
	@Column(name = "store_id",nullable = false)
	private Long storeId;
	
	@Column(name = "ordering_quantity",nullable = false)
	private Long orderingQuantity;
	
	@Column(name = "total",nullable = false)
	private Long total;
	
	@Column(name = "created_at")
	private Date createdAt;
}

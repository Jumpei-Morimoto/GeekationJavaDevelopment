package com.example.demo.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "orders")
public class OrdersReference {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "user_id",nullable = false)
	private Users users;
	
	@ManyToOne
	@JoinColumn(name = "item_id",nullable = false)
	private Items items;
	
	@Column(name = "purchase",nullable = false)
	private Long purchase;
	
	@ManyToOne
	@JoinColumn(name = "store_id",nullable = false)
	private Store store;
	
	@Column(name = "ordering_quantity",nullable = false)
	private Long orderingQuantity;
	
	@Column(name = "total",nullable = false)
	private Long total;
	
	@Column(name = "created_at")
	private Date createdAt;
}

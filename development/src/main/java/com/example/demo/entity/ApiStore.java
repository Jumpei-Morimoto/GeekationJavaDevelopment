package com.example.demo.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class ApiStore {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "store_name",nullable = false)
	private String storeName;
	
	@Column(name = "address",nullable = false)
	private String address;
	
	@OneToMany
	@Column(name = "inventory",nullable = false)
	List<ApiInventory> inventories;
	
	@OneToMany
	@Column(name = "orders",nullable = false)
	List <ApiOrders> orders;
	
	
	@Column(name = "created_at")
	  private Date createdAt;
	
	@Column(name = "updated_at")
	private Date updatedAt;
	
}

package com.example.demo.entity;

import java.io.Serializable;
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
@Table(name = "store")
public class Store implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "store_name",nullable = false)
	private String storeName;
	
	@Column(name = "address",nullable = false)
	private String address;
	
	@Column(name = "created_at")
	  private Date createdAt;
	
	@Column(name = "updated_at")
	private Date updatedAt;
	
}

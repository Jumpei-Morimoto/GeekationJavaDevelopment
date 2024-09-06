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
@Table(name = "sub_category")

public class Sub_Category {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "sub_category",nullable = false)
	private String subCategory;
	
	@Column(name = "category_id",nullable = false)
	private Long categoryId;
	
	@Column(name = "created_at")
	  private Date createdAt;
	
	@Column(name = "updated_at")
	private Date updatedAt;

}

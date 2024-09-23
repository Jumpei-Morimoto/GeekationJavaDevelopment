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
@Table(name = "items")

public class Items {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "item_name",nullable = false)
	private String itemName;
	
	@Column(name = "item_body",nullable = false)
	private String itemBody;
	
	@Column(name = "maker_id")
	private Long makerId;
	
	@Column(name = "item_image",nullable = false)
	private String itemImage;
	
	@Column(name = "purchase")
	private Long purchase;
	
	@Column(name = "list_price")
	private Long listPrice;
	
	@Column(name = "category_id")
	private Long categoryId;
	
	@Column(name = "sub_category_id")
	private Long subCategoryId;
	
	@Column(name = "sub_subcategory_id")
	private Long subSubCategoryId;
	
	@Column(name = "created_at")
	  private Date createdAt;
	
	@Column(name = "updated_at")
	private Date updatedAt;
	

}

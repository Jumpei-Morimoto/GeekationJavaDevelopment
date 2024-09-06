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
@Table(name = "items")

public class ItemsReference {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "item_name",nullable = false)
	private String itemName;
	
	@Column(name = "item_body",nullable = false)
	private String itemBody;
	
	@ManyToOne
	@JoinColumn(name = "maker_id")
	private Maker itemMakers;
	
	@Column(name = "item_image",nullable = false)
	private String itemImage;
	
	@Column(name = "purchase")
	private Long purchase;
	
	@Column(name = "list_price")
	private Long listPrice;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category itemCategories;
	
	@ManyToOne
	@JoinColumn(name = "sub_category_id")
	private Sub_Category itemSubCategories;
	
	@ManyToOne
	@JoinColumn(name = "sub_subcategory_id")
	private Sub_SubCategory itemSubSubCategories;
	
	@Column(name = "created_at")
	  private Date createdAt;
	
	@Column(name = "updated_at")
	private Date updatedAt;

}

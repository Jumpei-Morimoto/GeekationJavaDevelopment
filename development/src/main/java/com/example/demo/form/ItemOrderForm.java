package com.example.demo.form;

import java.io.Serializable;

import lombok.Data;

@Data

public class ItemOrderForm implements Serializable{

	private Long id;
	
	private Long user_id;
	
	private String item_id;

	private String purchase;
	
	private Long store_id;
	
	private String ordering_quantity;
	
	private Long price;
	
	private Long total;
	
    private String createdAt;
		
	
	
}

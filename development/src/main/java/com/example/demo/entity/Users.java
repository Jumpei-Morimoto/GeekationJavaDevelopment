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
@Table(name = "users")


public class Users {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;	
   
	@Column(name = "store_id",nullable = false)
	private Long storeName;

	@Column(name = "first_name",nullable = false)
	private String firstName;
	
	@Column(name = "last_name",nullable = false)
	private String lastName;
	
	@Column(name = "email",nullable = false)
	private String email;
	
	@Column(name = "position_id",nullable = false)
	private Long positionId;

	@Column(name = "role_id",nullable = false)
	private Long roleId;
	
	@Column(name = "phone",nullable = false)
	private String phone;
	
	@Column(name = "password",nullable = false)
	private String password;
	
	@Column(name = "created_at")
	  private Date createdAt;
	
	@Column(name = "updated_at")
	private Date updatedAt;
}

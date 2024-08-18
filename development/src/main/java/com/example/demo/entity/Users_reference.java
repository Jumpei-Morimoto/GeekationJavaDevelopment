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
@Table(name = "users")


public class Users_reference {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;	
    
	@ManyToOne
	@JoinColumn(name = "store_id",nullable = false)
	private Store stores;

	@Column(name = "first_name",nullable = false)
	private String firstName;
	
	@Column(name = "last_name",nullable = false)
	private String lastName;
	
	@Column(name = "email",nullable = false)
	private String email;
	
	@ManyToOne
	@JoinColumn(name = "position_id",nullable = false)
	private Positions positions;
	
	@ManyToOne
	@JoinColumn(name = "role_id",nullable = false)
	private Roles roles;
	
	@Column(name = "phone",nullable = false)
	private String phone;
	
	@Column(name = "password",nullable = false)
	private String password;
	
	@Column(name = "created_at")
	  private Date createdAt;
	
	@Column(name = "updated_at")
	private Date updatedAt;

}

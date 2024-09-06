package com.example.demo.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class CustomUserDetails implements UserDetails{
	
	private String username;
    private String password;
    private String email;
    private Long positionId;
    private Long roleId;
    private Long storeName;
    private Collection<? extends GrantedAuthority> authorities;
    
    
    public CustomUserDetails(String username, String password, String email, Long positionId,Long roleId, Long storeName,Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.positionId = positionId;
        this.roleId = roleId;
        this.storeName = storeName;
        
        this.authorities = authorities;
    }
    
    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
    
    public Long getStoreName() {
		return storeName;
	}

	public Long getRoleId() {
		return roleId;
	}

	public Long getPositionId() {
		return positionId;
	}

	public String getEmail() {
		return email;
	}
    
}

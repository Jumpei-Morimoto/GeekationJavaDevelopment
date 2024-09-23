package com.example.demo.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Users;
import com.example.demo.repository.UsersRepository;

@Service
public class UserDetailsImpl implements UserDetailsService{
	
private final UsersRepository repository;
	
	@Autowired
    public UserDetailsImpl(UsersRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
    }
	
	@Override
	public UserDetails loadUserByUsername(String username)throws UsernameNotFoundException{
		Users user = repository.findByEmail(username);
		if (user == null) {
				throw(new UsernameNotFoundException("not found"));
		}
	
		return new CustomUserDetails(user);
	}

}

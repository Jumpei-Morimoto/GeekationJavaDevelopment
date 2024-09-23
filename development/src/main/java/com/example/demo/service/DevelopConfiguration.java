package com.example.demo.service;

import java.util.List;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class DevelopConfiguration implements WebMvcConfigurer{
	/*ページネーション設定*/
	@Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {

        
        PageableHandlerMethodArgumentResolver resolver = new PageableHandlerMethodArgumentResolver();

        resolver.setMaxPageSize(3);

        argumentResolvers.add(resolver);
    }
	/*ここからログイン*/
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.formLogin(login -> login
                .loginProcessingUrl("/login")
                .loginPage("/").permitAll()
                .defaultSuccessUrl("/top")
                .failureUrl("/")
                .usernameParameter("email")
                .passwordParameter("password")
              ).logout(logout -> logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))	  
                .logoutSuccessUrl("/")
              ).authorizeHttpRequests(authz -> authz
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .requestMatchers("/","/API","/API2").permitAll()
                .requestMatchers("/item/{id}/delete","/maker/{id}/delete","/user/{id}/delete,/registration/maker_registration","/registration/administrator_registration","/registration/maker_registration","/edit/user_edit/{id}/edit","/edit/maker_edit/{id}/edit").hasRole("ADMIN")
                .anyRequest().authenticated()
              );
		return http.build();
    }
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(); 
	}

}

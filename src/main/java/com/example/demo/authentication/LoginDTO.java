package com.example.demo.authentication;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.demo.user.User;

import lombok.Data;


public @Data class LoginDTO {
	 private String username;
	 private String password;

	 public boolean validate(String username, String password) {

		 if (username == this.username && password == this.password) {
		     return true;
		 }
		 return false;
	 }

	}


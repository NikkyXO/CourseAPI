package com.example.courseAPI.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.courseAPI.entities.User;


public interface UserRepository extends JpaRepository<User, Long> {
	
	
	Optional<User> findByUsername(String username);
	
	Optional<User> findByEmail(String email);

	
	boolean existsByUsername(String username);
	
	boolean existsByEmail(String email);
	

}

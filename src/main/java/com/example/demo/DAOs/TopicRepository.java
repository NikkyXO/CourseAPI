package com.example.demo.DAOs;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.Topic;

public interface TopicRepository extends CrudRepository<Topic, String> {
	
	
	
}

package com.example.courseAPI.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.courseAPI.entities.Topic;

public interface TopicRepository extends CrudRepository<Topic, String> {
	
	
	
}

package com.example.demo.DAOs;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.Course;

public interface CourseRepository extends CrudRepository<Course, String> {
	
	
	
}

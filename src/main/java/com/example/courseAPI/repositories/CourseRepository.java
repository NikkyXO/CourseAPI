package com.example.courseAPI.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.courseAPI.entities.Course;

public interface CourseRepository extends CrudRepository<Course, String> {
	
	
	
}

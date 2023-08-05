package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DAOs.CourseRepository;
import com.example.demo.DAOs.TopicRepository;
import com.example.demo.models.Course;

@Service
public class CourseService {

	@Autowired
	private CourseRepository courseRepository;

	
	
	public List<Course> getAllCourses(){
		List<Course> courses = new ArrayList<>();
		courseRepository.findAll()
		.forEach(courses::add);
		
		return courses;
	}
	
	public Course getCourseByID(String id) {
		var course = courseRepository.findById(id).get();
		System.out.print(course);
		return course;
	}
	
	public void addCourse(Course course) {
		courseRepository.save(course);
	}
	
	public void updateCourse(String id, Course course) {
		courseRepository.save(course);
	}
	
	public void deleteCourse(String id) {
		courseRepository.deleteById(id);
		
		
	}

}

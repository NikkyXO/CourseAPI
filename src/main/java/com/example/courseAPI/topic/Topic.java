package com.example.courseAPI.topic;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import com.example.courseAPI.course.Course;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;

// @JsonIgnore

@Entity
@Table(name="topic")
public class Topic {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Schema(hidden = true)
	private Long id;
	
	private String name;
	private String description;
	

	public Topic() {
		
	}

	public Topic(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}

	public Long getId() {
		return id;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
//	public List<Course> getCourses() {
//		return courses;
//	}
//	
	public void addCourse(Course course) {
//		courses.add(course);
		course.setTopic(this);
	}
//	
//	public void removeCourse(Course course) {
//		courses.remove(course);
//		course.setTopic(null);
//	}


}

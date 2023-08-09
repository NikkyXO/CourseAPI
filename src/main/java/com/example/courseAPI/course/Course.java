package com.example.courseAPI.course;

import com.example.courseAPI.topic.Topic;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name="course")
public class Course {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Schema(hidden = true)
	private Long id;
	

	private String name;
	private String description;
	
	
	
//    @JoinColumn(name="topic_id", referencedColumnName = "id")
//	@JsonIgnoreProperties("course")
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Schema(hidden = true)
	private Topic topic;
	

	public Course() {
		
	}
	
	
	public Course(String name, String description) {
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
	
	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}
	

}

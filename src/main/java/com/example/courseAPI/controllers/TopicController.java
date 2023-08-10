package com.example.courseAPI.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.courseAPI.entities.Topic;
import com.example.courseAPI.services.CourseService;
import com.example.courseAPI.services.TopicService;

@RestController
public class TopicController {
	
	@Autowired
	private TopicService topicService;

	
	@GetMapping("/topics")
	public List<Topic> getAlltopics() {
		return topicService.getAllTopics();
	}
	
	@GetMapping("/topics/{id}")
	public Topic getTopicByID(@PathVariable String id) {
		return topicService.getTopicByID(id);
	}
	
	@PostMapping("/topics")
	public Topic createNewTopic(@RequestBody Topic topic) {
		return topicService.addTopic(topic); 
	}
	
	@PutMapping("/topics/{id}")
	public void updateTopic(@PathVariable String id, @RequestBody Topic topic) {
		topicService.updateTopic(id, topic); 
	}
	
	@DeleteMapping("/topics/{id}")
	public void deleteTopic(@PathVariable String id) {
		topicService.deleteTopic(id); 
	}

}

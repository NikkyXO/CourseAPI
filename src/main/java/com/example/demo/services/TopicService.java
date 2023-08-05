package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DAOs.TopicRepository;
import com.example.demo.models.Topic;

@Service
public class TopicService {

	@Autowired
	private TopicRepository topicRepository;
	
	
	public List<Topic> getAllTopics(){
		List<Topic> topics = new ArrayList<>();
		topicRepository.findAll()
		.forEach(topics::add);
		
		return topics;
	}
	
	public Topic getTopicByID(String id) {
		var topic = topicRepository.findById(id).get();
		System.out.print(topic);
		return topic;
	}
	
	public Topic addTopic(Topic topic) {
		return topicRepository.save(topic);
	}
	
	public void updateTopic(String id, Topic topic) {
		topicRepository.save(topic);
	}
	
	public void deleteTopic(String id) {
		topicRepository.deleteById(id);
		
		
	}

}

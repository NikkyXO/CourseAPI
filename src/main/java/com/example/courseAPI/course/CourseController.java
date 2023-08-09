package com.example.courseAPI.course;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.courseAPI.topic.Topic;
import com.example.courseAPI.topic.TopicService;

@RestController
public class CourseController {
	
	@Autowired
	private TopicService topicService;
	
	@Autowired
	private CourseService courseService;
	
	
    @RequestMapping("/")
    public class RootController {
        @RequestMapping(method = RequestMethod.GET)
        public String swaggerUi() {
            return "redirect:/swagger-ui.html";
        }
    }
	
	@GetMapping("/courses")
	public List<Course> getAllCourses() {
		return courseService.getAllCourses();
	}
	
	@GetMapping("/topics/{id}/courses") // review
	public List<Course> getCoursesByTopicId() {
		return courseService.getAllCourses();
	}
	
	@GetMapping("/topics/{topicId}/courses/{courseId}")
	public Course getCourse(@PathVariable String id) {
		return courseService.getCourseByID(id);
	}
	
	@GetMapping("/courses/{id}")
	public Course getCourseByID(@PathVariable String id) {
		return courseService.getCourseByID(id);
	}
	
	@PostMapping("/topics/{topicId}/courses")
	public Course createNewCourse(@RequestBody Course course, @RequestParam String topicId) {
		Topic topic = topicService.getTopicByID(topicId);
		if(topic == null) {
			throw new IllegalArgumentException("Topic not found");
		}
		topic.addCourse(course);
		courseService.addCourse(course);
		return course;
	}
	
	@PutMapping("/courses/{id}")
	public void updateCourse(@PathVariable String id, @RequestBody Course course) {
		courseService.updateCourse(id, course); 
	}
	
	@DeleteMapping("/courses/{id}")
	public void deleteCourse(@PathVariable String id) {
		courseService.deleteCourse(id); 
	}

}

package com.commons.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.commons.DTO.CourseDTO;
import com.commons.exception.CourseException;
import com.commons.model.Course;
import com.commons.service.CourseService;

@RestController
public class CourseController {

	@Autowired
	public CourseService courseService;
	
	@PostMapping("/course")
	public ResponseEntity<Course> registerCourseHandler(@RequestBody Course course) throws CourseException{
		
		 Course crs= courseService.registerCourse(course);
		
		 return new ResponseEntity<Course>(crs,HttpStatus.OK);
	}
	
	@GetMapping("/courses")
	public ResponseEntity<List<Course>> allCourseHandler(){
		
		List<Course> courses= courseService.getAllCourses();
		
		return new ResponseEntity<List<Course>>(courses,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/course/")
	public ResponseEntity<CourseDTO> deleteCourseHandler(@RequestParam Integer id) throws CourseException{
	  
		CourseDTO cdo=courseService.removeCourse(id);
		
		return new ResponseEntity<CourseDTO>(cdo,HttpStatus.OK);
		
	}
	
	@GetMapping("/course/topic")
	public ResponseEntity<List<CourseDTO>> listByTopicHandler(@RequestParam("topicName") String topic) throws CourseException{
		
		List<CourseDTO> cdto=courseService.getCourseByTopic(topic);
		
		return new ResponseEntity<List<CourseDTO>>(cdto,HttpStatus.OK);
		
	}
	 
	
}

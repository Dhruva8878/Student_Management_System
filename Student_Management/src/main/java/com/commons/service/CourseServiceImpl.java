package com.commons.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.commons.DTO.CouresStudentDTO;
import com.commons.DTO.CourseDTO;
import com.commons.DTO.StudentCourseDTO;
import com.commons.exception.CourseException;
import com.commons.exception.StudentException;
import com.commons.model.Course;
import com.commons.repo.CourseRepo;
import com.commons.repo.StudentRepo;

@Service
public class CourseServiceImpl implements CourseService {
    
	@Autowired 
	private StudentRepo studentRepo;
	
	@Autowired
	private CourseRepo courseRepo;
	
	@Override
	public Course registerCourse(Course course) throws CourseException {
		
		Optional<Course> crs=courseRepo.findById(course.getCourseId());
		
		if(crs.isEmpty()) {
			throw new CourseException("Course Already Exists");
		}
		
		return courseRepo.save(crs.get());
	}

	@Override
	public List<Course> getAllCourses() {
		
		List<Course> courses=courseRepo.findAll();
		
		return courses;
	}

	@Override
	public CourseDTO removeCourse(Integer courseId) throws CourseException {
		
		Course course=courseRepo.findById(courseId).orElseThrow(()-> new CourseException("No Course Found with this id :"+courseId));
		
		CourseDTO cdto=new CourseDTO(course.getCourseId(),
				                     course.getCourseName(),
				                     course.getDescription(),
				                     course.getCourseType(),
				                     course.getDuration(),
				                     course.getTopics());
		
		return cdto;
	}

	@Override
	public List<CourseDTO> getCourseByTopic(String topic) throws CourseException {
		
		List<Course> courses=courseRepo.getCoursesByTopic(topic);
		
		if(courses.isEmpty()) {
			throw new CourseException("No courses found with that topic :"+topic);
		}
		
		List<CourseDTO> cdto = new ArrayList<>();
		
		for(Course course: courses) {
			
			CourseDTO cd=new CourseDTO(course.getCourseId(),
                    course.getCourseName(),
                    course.getDescription(),
                    course.getCourseType(),
                    course.getDuration(),
                    course.getTopics());
           
			cdto.add(cd);
			
		}
		
		return cdto;
	}


}

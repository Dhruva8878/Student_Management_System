package com.commons.service;

import java.util.List;

import com.commons.DTO.CouresStudentDTO;
import com.commons.DTO.CourseDTO;
import com.commons.DTO.StudentCourseDTO;
import com.commons.exception.CourseException;
import com.commons.exception.StudentException;
import com.commons.model.Course;

public interface CourseService {

	 public Course registerCourse(Course course);
	 
	 public List<Course> getAllCourses();
	 
	 public CourseDTO removeCourse(Integer id);
	 
	 public List<CourseDTO> getCourseByTopic(String topic);
	 
	 public StudentCourseDTO assignCourseToStudent(Integer studentId,Integer courseId) throws CourseException, StudentException;
		
	public CouresStudentDTO getStudentsFromCourse(Integer courseId) throws CourseException;
	 
}

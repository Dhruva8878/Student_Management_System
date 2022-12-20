package com.commons.service;

import java.util.List;

import com.commons.DTO.CouresStudentDTO;
import com.commons.DTO.CourseDTO;
import com.commons.DTO.StudentCourseDTO;
import com.commons.exception.CourseException;
import com.commons.exception.StudentException;
import com.commons.model.Course;

public class CourseServiceImpl implements CourseService {

	@Override
	public Course registerCourse(Course course) {
		
		
		
		return null;
	}

	@Override
	public List<Course> getAllCourses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CourseDTO removeCourse(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CourseDTO> getCourseByTopic(String topic) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StudentCourseDTO assignCourseToStudent(Integer studentId, Integer courseId)
			throws CourseException, StudentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CouresStudentDTO getStudentsFromCourse(Integer courseId) throws CourseException {
		// TODO Auto-generated method stub
		return null;
	}

}

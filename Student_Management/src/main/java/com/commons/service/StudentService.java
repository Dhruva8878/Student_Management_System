package com.commons.service;

import java.util.List;

import com.commons.DTO.STUpdateDTO;
import com.commons.DTO.StudentAddressDTO;
import com.commons.DTO.StudentCourseDTO;
import com.commons.DTO.StudentDTO;
import com.commons.exception.CourseException;
import com.commons.exception.StudentException;
import com.commons.model.Address;
import com.commons.model.Course;
import com.commons.model.Student;

public interface StudentService {
     
public StudentDTO registerStudent(StudentDTO studentDTO) throws StudentException;
	
	public Student getStudentById(Integer studentId) throws StudentException ;
	
	public List<Course> getAllCoursesAdminPurpose(Integer studentId) throws StudentException ;
	
	public List<StudentDTO> getStudentByName(String name) throws StudentException;
	
	public StudentDTO updateEmailAndMobile(STUpdateDTO dto) throws StudentException;
	
	public StudentDTO updateStudentAddress(StudentAddressDTO addressDTO) throws StudentException;
	
	public StudentCourseDTO getStudentCourses(Integer studentId) throws StudentException;
	
	public StudentCourseDTO leaveCourse(Integer studentId,Integer courseId) throws StudentException, CourseException;
		
	public StudentDTO addNewAddress(StudentAddressDTO addressDTO) throws StudentException;
	
}

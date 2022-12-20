package com.commons.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.commons.DTO.STUpdateDTO;
import com.commons.DTO.StudentAddressDTO;
import com.commons.DTO.StudentCourseDTO;
import com.commons.DTO.StudentDTO;
import com.commons.exception.CourseException;
import com.commons.exception.StudentException;
import com.commons.service.StudentService;

@RestController
public class StudentController {

	@Autowired
	private StudentService studentService;

	@PostMapping("/students/")
	public ResponseEntity<StudentDTO> registerStudentHandler(@RequestBody StudentDTO studentDTO)
			throws StudentException {

		StudentDTO registredStudent = studentService.registerStudent(studentDTO);

		return new ResponseEntity<StudentDTO>(registredStudent, HttpStatus.CREATED);
	}

	@GetMapping("/students/")
	public ResponseEntity<List<StudentDTO>> getStudentsByNameHandler(@RequestParam("name") String name)
			throws StudentException {

		List<StudentDTO> studentsList = studentService.getStudentByName(name);

		return new ResponseEntity<List<StudentDTO>>(studentsList, HttpStatus.OK);
	}

	@PatchMapping("/students/update/")
	public ResponseEntity<StudentDTO> updateEmailAndMobileNumberHandler(@RequestBody STUpdateDTO studentUpdateDTO)
			throws StudentException {

		StudentDTO studentDTO = studentService.updateEmailAndMobile(studentUpdateDTO);

		return new ResponseEntity<StudentDTO>(studentDTO, HttpStatus.OK);
	}

	@PatchMapping("/students/update/address")
	public ResponseEntity<StudentDTO> updateAddressHandler(@RequestBody StudentAddressDTO studentAddressDTO)
			throws StudentException {

		StudentDTO studentDTO = studentService.updateStudentAddress(studentAddressDTO);

		return new ResponseEntity<StudentDTO>(studentDTO, HttpStatus.OK);
	}
	
	@PutMapping("/students/add/address")
	public ResponseEntity<StudentDTO> addAddressHandler(@RequestBody StudentAddressDTO studentAddressDTO)
			throws StudentException {

		StudentDTO studentDTO = studentService.addNewAddress(studentAddressDTO);
		
		return new ResponseEntity<StudentDTO>(studentDTO, HttpStatus.OK);
	}

	@DeleteMapping("/students/courses")
	public ResponseEntity<StudentCourseDTO> leaveCourseHandler(@RequestParam("studentId") Integer studentId, 
															@RequestParam("courseId") Integer courseId)
															throws StudentException, CourseException {

		StudentCourseDTO studentCourse = studentService.leaveCourse(studentId,  courseId);

		return new ResponseEntity<StudentCourseDTO>(studentCourse, HttpStatus.OK);
	}

	@GetMapping("/students/courses")
	public ResponseEntity<StudentCourseDTO> getStudentCoursesHandler(@RequestParam("studentId") Integer studentId) throws StudentException{

		StudentCourseDTO studentCourse = studentService.getStudentCourses(studentId) ;

		return new ResponseEntity<StudentCourseDTO>(studentCourse, HttpStatus.OK);
	}
	
}

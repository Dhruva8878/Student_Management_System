package com.commons.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.commons.DTO.CourseDTO;
import com.commons.DTO.STUpdateDTO;
import com.commons.DTO.StudentAddressDTO;
import com.commons.DTO.StudentCourseDTO;
import com.commons.DTO.StudentDTO;
import com.commons.exception.CourseException;
import com.commons.exception.StudentException;
import com.commons.model.Address;
import com.commons.model.Course;
import com.commons.model.Student;
import com.commons.repo.AddressRepo;
import com.commons.repo.CourseRepo;
import com.commons.repo.StudentRepo;

import springfox.documentation.swagger2.mappers.ModelMapper;

@Service
public class StudentServiceImpl implements StudentService{

	
	@Autowired
	private StudentRepo studentRepo;
	
	@Autowired
	private CourseRepo courseRepo;
	
	@Autowired
	private AddressRepo addressRepo;
	
	@Override
	public StudentDTO registerStudent(StudentDTO studentDTO) throws StudentException {
      
		Student stds=new Student();
		
		
		stds.setName(studentDTO.getName());
		stds.setDateOfBirth(studentDTO.getDateOfBirth());
		stds.setEmail(studentDTO.getEmail());
		stds.setGender(studentDTO.getGender());
		stds.setMobileNumber(studentDTO.getMobileNumber());
		List<Address> addr= stds.getAddresses();
		
		addr.addAll(studentDTO.getAddress());
		
		studentRepo.save(stds);
		
		return studentDTO;
	}

	@Override
	public Student getStudentById(Integer studentId) throws StudentException {
		
		Optional<Student> stds=studentRepo.findById(studentId);
		
		if(stds.isPresent()) {
			
			return stds.get();
		}
		else {
			throw new StudentException("No Student is found with this id : "+studentId);
		}
	}

	@Override
	public List<Course> getAllCoursesAdminPurpose(Integer studentId) throws StudentException {
		
		return studentRepo.findById(studentId).orElseThrow(()-> new StudentException("Not Found : "+studentId)).getCourses();
	}

	@Override
	public List<StudentDTO> getStudentByName(String name) throws StudentException {
		
		List<Student> stds=studentRepo.getStudentsByName(name);
		
		if(stds.isEmpty()) {
			throw new StudentException("No Students are found with name :"+name);
		}
		
		List<StudentDTO> studs= new ArrayList<>();
		
		for(Student st : stds) {
			
			StudentDTO sd=new StudentDTO();
			
			sd.setId(st.getId());
			sd.setName(st.getName());
			sd.setDateOfBirth(st.getDateOfBirth());
			sd.setEmail(st.getEmail());
			sd.setGender(st.getGender());
			sd.setMobileNumber(st.getMobileNumber());
			sd.getAddress().addAll(st.getAddresses());
			
			studs.add(sd);
			
			
		}
				             
		return studs;
	}

	@Override
	public StudentDTO updateEmailAndMobile(STUpdateDTO dto) throws StudentException {
		
		Optional<Student> stds=studentRepo.findById(dto.getId());
		
		if(stds.isEmpty()) {
			throw new StudentException("Invalid Request");
		}
		else {
			stds.get().setEmail(dto.getNewMail());
			stds.get().setMobileNumber(dto.getNewMobileNumber());
			
			studentRepo.save(stds.get());
			
              StudentDTO sd=new StudentDTO();
			
			sd.setId(stds.get().getId());
			sd.setName(stds.get().getName());
			sd.setDateOfBirth(stds.get().getDateOfBirth());
			sd.setEmail(stds.get().getEmail());
			sd.setGender(stds.get().getGender());
			sd.setMobileNumber(stds.get().getMobileNumber());
			sd.getAddress().addAll(stds.get().getAddresses());
			
			
			return sd;			
		}
		
	}

	@Override
	public StudentDTO updateStudentAddress(StudentAddressDTO addressDTO) throws StudentException {
		
		Optional<Student> st=studentRepo.findById(addressDTO.getStudentId());
		
        if(st.isEmpty()) {
        	throw new StudentException("Invalid Request");
        }
        
        List<Address> addr= st.get().getAddresses();
        
        for(Address ad:addr) {
        	if(ad.getAddressId().equals(addressDTO.getAddress().getAddressId())) {
        		addressRepo.save(addressDTO.getAddress());
        	}
        }
        
        
        StudentDTO sd=new StudentDTO();
		
		sd.setId(st.get().getId());
		sd.setName(st.get().getName());
		sd.setDateOfBirth(st.get().getDateOfBirth());
		sd.setEmail(st.get().getEmail());
		sd.setGender(st.get().getGender());
		sd.setMobileNumber(st.get().getMobileNumber());
		sd.getAddress().addAll(st.get().getAddresses());
		
		
		return sd;	
        
		
	}

	@Override
	public StudentCourseDTO getStudentCourses(Integer studentId) throws StudentException {
		
		
		Optional<Student> stds=studentRepo.findById(studentId);
		
		if(stds.isEmpty()) {
			throw new StudentException("Invalid Access");
		}
		
		StudentCourseDTO sdo=new StudentCourseDTO();
		
		sdo.setName(stds.get().getName());
		
		sdo.setStudentId(stds.get().getId());
		
        List<CourseDTO> cdto= sdo.getCourses();
        
        List<Course> course= stds.get().getCourses();
        
        for(Course c:course) {
        	
        	CourseDTO cd=new CourseDTO();
        	
        	cd.setCourseId(c.getCourseId());
        	cd.setCourseName(c.getCourseName());
        	cd.setCourseType(c.getCourseType());
        	cd.setDerscription(c.getDescription());
        	cd.setDuration(c.getDescription());
        	cd.setTopics(c.getTopics());
        	
        	cdto.add(cd);
        	
        }
		
        return sdo;
	}

	@Override
	public StudentCourseDTO leaveCourse(Integer studentId, Integer courseId)
			throws StudentException, CourseException {
		
		Course course=courseRepo.findById(courseId).orElseThrow(()-> new CourseException("Invalid CourseId"));
		Student student=studentRepo.findById(studentId).orElseThrow(()-> new StudentException("Invalid StudentId"));
 
		if(student.getCourses().contains(course)) {
			student.getCourses().remove(course);
		}
		if(course.getStudents().contains(student)) {
			course.getStudents().remove(student);
		}
		
		Student studs=studentRepo.save(student);
		courseRepo.save(course);
	
		StudentCourseDTO sdto=new StudentCourseDTO();
		
		sdto.setName(studs.getName());
		sdto.setStudentId(studentId);
		List<CourseDTO> cdto= sdto.getCourses();
		
        
        List<Course> courses= studs.getCourses();
        
        for(Course c:courses) {
        	
        	CourseDTO cd=new CourseDTO();
        	
        	cd.setCourseId(c.getCourseId());
        	cd.setCourseName(c.getCourseName());
        	cd.setCourseType(c.getCourseType());
        	cd.setDerscription(c.getDescription());
        	cd.setDuration(c.getDescription());
        	cd.setTopics(c.getTopics());
        	
        	cdto.add(cd);
        	
        }
		
        return sdto;
		
	}

	@Override
	public StudentDTO addNewAddress(StudentAddressDTO addressDTO) throws StudentException {
		
		Student studs=studentRepo.findById(addressDTO.getStudentId()).orElseThrow(()->new StudentException("Invalid credentials"));
		
		List<Address> addrs=studs.getAddresses();
		
		List<Address> newAddress = addrs.stream()
				                           .filter(address ->
				                            address.getAddressType().equals(addressDTO.getAddress().getAddressType()))
				                           .collect(Collectors.toList());
		if(newAddress.size()!=0) {
			throw new StudentException("The Address is already exist in the students address list");
		}
		
		studs.getAddresses().add(addressDTO.getAddress());
		
		Student updatedStudent=studentRepo.save(studs);
		
		 StudentDTO sd=new StudentDTO();
			
			sd.setId(updatedStudent.getId());
			sd.setName(updatedStudent.getName());
			sd.setDateOfBirth(updatedStudent.getDateOfBirth());
			sd.setEmail(updatedStudent.getEmail());
			sd.setGender(updatedStudent.getGender());
			sd.setMobileNumber(updatedStudent.getMobileNumber());
			sd.getAddress().addAll(updatedStudent.getAddresses());
			
		
		return sd;
	}
	
	

}

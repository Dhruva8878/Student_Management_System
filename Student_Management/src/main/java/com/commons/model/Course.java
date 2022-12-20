package com.commons.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer courseId;
	
	private String courseName;
    
	private String description;
	
	private String duration;
    
	private String courseType;
    
	private String topics; 
    
	@JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Student> students;
}

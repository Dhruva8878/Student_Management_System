package com.commons.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.commons.enums.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;
	private String name;
	private String mobileNumber;
	private String email;
	private Gender gender;
	private LocalDate dateOfBirth;
	 
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL)
	private List<Address> addresses;
	
	@JsonIgnore
	@ManyToMany(cascade = CascadeType.ALL ,mappedBy = "students")
	private List<Course> courses; 
	
	
	
}

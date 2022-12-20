package com.commons.DTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.commons.enums.Gender;
import com.commons.model.Address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
	
	private Integer Id;
	
	private String name;
	private String mobileNumber;
	private Gender gender;
	private LocalDate dateOfBirth;
	private String email;
   
	
	private List<Address> address = new ArrayList<>();
}

package com.commons.DTO;

import java.time.LocalDate;

import com.commons.model.Address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentAddressDTO {
   
	private Integer studentId;
	
	private Address address;
	
} 

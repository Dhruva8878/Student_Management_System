package com.commons.DTO;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class STUpdateDTO {
   
	private Integer id;
	
	private LocalDate localDate;
	
	private String newMail;
	
	private String newMobileNumber;
	
}

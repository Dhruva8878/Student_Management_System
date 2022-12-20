package com.commons.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {
	
	private Integer courseId;
	
	private String courseName;
	
	private String derscription;
	
	private String courseType;
	
	private String duration;
	
	private String Topics;

}

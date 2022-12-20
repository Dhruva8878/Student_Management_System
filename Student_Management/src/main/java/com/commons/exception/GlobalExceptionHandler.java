package com.commons.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
     
	
	@ExceptionHandler(StudentException.class)
	public ResponseEntity<ErrorDetails> exceptionhandler(StudentException e,WebRequest wr){
		
		ErrorDetails er=new ErrorDetails(wr.getDescription(false), e.getMessage(), LocalDateTime.now());
	   
		return new ResponseEntity<ErrorDetails>(er,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(CourseException.class)
	public ResponseEntity<ErrorDetails> exceptionhandler(CourseException e,WebRequest wr){
		
		ErrorDetails er=new ErrorDetails(wr.getDescription(false), e.getMessage(), LocalDateTime.now());
	   
		return new ResponseEntity<ErrorDetails>(er,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> exceptionhandler(Exception e,WebRequest wr){
		
		ErrorDetails er=new ErrorDetails(wr.getDescription(false), e.getMessage(), LocalDateTime.now());
	   
		return new ResponseEntity<ErrorDetails>(er,HttpStatus.BAD_REQUEST);
	}
}

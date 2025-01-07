package com.patientregistrationapi.infra;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ErrorHandler {
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<?> handleNotFound() {
		 return ResponseEntity.notFound().build();
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleBadRequest(MethodArgumentNotValidException ex) {
		var errors = ex.getFieldErrors();
		
		return ResponseEntity.badRequest().body(errors.stream().map(ErrorsDto::new).toList());
	}
	
	public record ErrorsDto(String field, String message) {
		
		public ErrorsDto(FieldError error) {
			this(error.getField(), error.getDefaultMessage());
		}
	}
}

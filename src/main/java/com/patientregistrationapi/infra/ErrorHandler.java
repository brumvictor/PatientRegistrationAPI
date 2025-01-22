package com.patientregistrationapi.infra;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ErrorHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleBadRequest(MethodArgumentNotValidException ex) {
		var errors = ex.getFieldErrors();
		
		 // Retorna detalhes dos campos inválidos no corpo da resposta
	    return ResponseEntity.badRequest().body(
	        errors.stream()
	              .map(ErrorsDto::new) // Mapeia para a classe DTO definida
	              .toList()
	    );
	}
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<?> handleNotFound() {
		return ResponseEntity.status(404).body("Entity Not Found");
	}
	
	@ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handleAccessDenied() {
        return ResponseEntity.status(403).body("Access Denied");
    }
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleGenericException(Exception ex) {
	     return ResponseEntity.status(500).body("An unexpected error occurred");
	}
	
	public record ErrorsDto(String field, String message) {
		
		public ErrorsDto(FieldError error) {
			this(error.getField(), error.getDefaultMessage());
		}
	}
}

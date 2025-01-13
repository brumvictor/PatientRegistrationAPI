package com.patientregistrationapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patientregistrationapi.users.AuthDto;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class AuthController {
	
	@Autowired
	private AuthenticationManager manager;
	
	@PostMapping
	public ResponseEntity<?> login(@RequestBody @Valid AuthDto dto) {
		var token = new UsernamePasswordAuthenticationToken(dto.login(), dto.password());
		var authenticator = manager.authenticate(token);
		
		return ResponseEntity.ok("");
	}
		
}

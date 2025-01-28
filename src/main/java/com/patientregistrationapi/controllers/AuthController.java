package com.patientregistrationapi.controllers;

import com.patientregistrationapi.users.RegisterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patientregistrationapi.infra.JWTTokenDTO;
import com.patientregistrationapi.infra.TokenService;
import com.patientregistrationapi.users.AuthDto;
import com.patientregistrationapi.users.User;
import com.patientregistrationapi.users.UserRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
@Tag(name = "Authentication", description = "Endpoints for user authentication")
public class AuthController {

	@Autowired
	private AuthenticationManager manager;

	@Autowired
	private TokenService tokenService;

	@Autowired
    private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping
	@Operation(summary = "Authenticate a user", description = "Validates user credentials and returns a JWT token.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Authentication successful, returns the JWT token."),
        @ApiResponse(responseCode = "400", description = "Invalid request data provided"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
	public ResponseEntity<?> login(@RequestBody @Valid AuthDto dto) {
		var token = new UsernamePasswordAuthenticationToken(dto.login(), dto.password());
		var authenticator = manager.authenticate(token);
		
		var tokenJWT = tokenService.generateToken((User) authenticator.getPrincipal());
		
		return ResponseEntity.ok(new JWTTokenDTO(tokenJWT));
	}
	
	@PostMapping("/register")
    @Operation(summary = "Register a user", description = "Registers a new user in the system.")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "User successfully registered."),
        @ApiResponse(responseCode = "400", description = "Invalid request data provided."),
        @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    public ResponseEntity<?> register(@RequestBody @Valid RegisterDto registerDto) {
        // Check if user already exists
        if (userRepository.findByLogin(registerDto.login()) != null) {
            return ResponseEntity.badRequest().body("User with this login already exists.");
        }

        // Create and save the new user
        User newUser = new User();
        newUser.setLogin(registerDto.login());
        newUser.setPassword(passwordEncoder.encode(registerDto.password())); // Hash the password using a password encoder
        userRepository.save(newUser);

        return ResponseEntity.status(201).body("User registered successfully.");
    }
}

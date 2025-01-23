package com.patientregistrationapi.controllers;

import jakarta.validation.constraints.NotBlank;

public record RegisterDto(
	@NotBlank(message = "Login is required.")
    String login,

    @NotBlank(message = "Password is required.")
    String password
) {}

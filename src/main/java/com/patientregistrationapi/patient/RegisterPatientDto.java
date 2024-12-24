package com.patientregistrationapi.patient;

import java.time.LocalDate;

import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

public record RegisterPatientDto(
		
		@NotBlank
		String name,
		
		@NotNull
		@Past
		LocalDate birthDate,
		
		@Enumerated
		@NotNull
		BloodGroup bloodGroup,
		
		@NotBlank
		String phoneNumber,
		
		@NotBlank
		@Email
		String email,
		
		@NotBlank
		String address) {
}

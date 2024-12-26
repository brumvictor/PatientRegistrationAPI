package com.patientregistrationapi.patient;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

public record UpdatePatientDto(
		@NotNull
		Long id,
		
		String name, 
		LocalDate birthDate, 
		BloodGroup bloodGroup, 
		String phoneNumber, 
		String email, 
		String address) {
}

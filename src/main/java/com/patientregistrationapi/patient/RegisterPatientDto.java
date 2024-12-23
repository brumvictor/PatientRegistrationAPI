package com.patientregistrationapi.patient;

public record RegisterPatientDto(
		String name, 
		String birthDate, 
		BloodGroup bloodGroup, 
		String phoneNumber, 
		String email, 
		String address) {
}

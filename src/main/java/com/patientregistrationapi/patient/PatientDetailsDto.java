package com.patientregistrationapi.patient;

import java.time.LocalDate;

public record PatientDetailsDto( 
		Long id, 
		String name, 
		LocalDate birthDate, 
		BloodGroup bloodGroup, 
		String phoneNumber, 
		String email, 
		String address,
		Boolean active) {
	
	public PatientDetailsDto(Patient patient) {
		this(	
				patient.getId(),
				patient.getName(), 
				patient.getBirthDate(), 
				patient.getBloodGroup(), 
				patient.getPhoneNumber(), 
				patient.getEmail(), 
				patient.getAddress(),
				patient.getActive());
	}
}

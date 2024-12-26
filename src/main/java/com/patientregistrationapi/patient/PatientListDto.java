package com.patientregistrationapi.patient;

import java.time.LocalDate;

public record PatientListDto(String name, LocalDate birthDate, BloodGroup bloodGroup, String phoneNumber, String email, String address) {
	public PatientListDto(Patient patient) {
		this(
				patient.getName(), 
				patient.getBirthDate(), 
				patient.getBloodGroup(), 
				patient.getPhoneNumber(), 
				patient.getEmail(), 
				patient.getAddress());
	}
}

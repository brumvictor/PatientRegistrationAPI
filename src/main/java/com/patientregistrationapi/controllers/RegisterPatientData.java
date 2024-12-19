package com.patientregistrationapi.controllers;

public record RegisterPatientData(
		String name, 
		String birthDate, 
		BloodGroup bloodGroup, 
		String phoneNumber, 
		String email, 
		String adress) {
}

package com.patientregistrationapi.patient;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "Patient")
@Entity(name = "patients")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Patient {
	
	public Patient(RegisterPatientDto dto) {
		this.name=dto.name();
		this.birthDate=dto.birthDate();
		this.bloodGroup=dto.bloodGroup();
		this.phoneNumber=dto.phoneNumber();
		this.email=dto.email();
		this.address=dto.address();
	}
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String birthDate;
	
	@Enumerated(EnumType.STRING)
	private BloodGroup bloodGroup; 
	
	private String phoneNumber;
	private String email;
	private String address;
}

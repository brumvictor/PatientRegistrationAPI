package com.patientregistrationapi.patient;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
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
	
	public Patient() {
	}

	public Patient(RegisterPatientDto dto) {
		this.active = true;
		this.name = dto.name();
		this.birthDate = dto.birthDate();
		this.bloodGroup = BloodGroup.fromValue(dto.bloodGroup().toString());
		this.phoneNumber = dto.phoneNumber();
		this.email = dto.email();
		this.address = dto.address();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private LocalDate birthDate;

	@Enumerated(EnumType.STRING)
	private BloodGroup bloodGroup;

	private String phoneNumber;
	private String email;
	private String address;
	
	private Boolean active;

	// Getters
	public Long getId() {
		// TODO Auto-generated method stub
		return id;
	}

	public String getName() {
		return name;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public BloodGroup getBloodGroup() {
		return bloodGroup;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public String getAddress() {
		return address;
	}
		
	public Boolean getActive() {
		return active;
	}

	public void updateInfo(@Valid UpdatePatientDto dto) {

		if (dto.name() != null) {
			this.name = dto.name();
		}

		if (dto.birthDate() != null) {
			this.birthDate = dto.birthDate();
		}

		if (dto.bloodGroup() != null) {
			this.bloodGroup = dto.bloodGroup();
		}

		if (dto.phoneNumber() != null) {
			this.phoneNumber = dto.phoneNumber();
		}

		if (dto.email() != null) {
			this.email = dto.email();
		}

		if (dto.address() != null) {
			this.address = dto.address();
		}
	}

	public void deactivatePatient() {
		this.active = false;
	}
	
	public void activatePatient() {
		this.active = true;
	}

	
}

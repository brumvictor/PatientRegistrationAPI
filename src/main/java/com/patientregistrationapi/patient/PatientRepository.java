package com.patientregistrationapi.patient;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {

	List<Patient> findAllByActiveTrue();
}

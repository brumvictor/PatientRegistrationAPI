package com.patientregistrationapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patientregistrationapi.patient.Patient;
import com.patientregistrationapi.patient.PatientListDto;
import com.patientregistrationapi.patient.PatientRepository;
import com.patientregistrationapi.patient.RegisterPatientDto;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/patients")
public class PatientController {
	
	@Autowired
	private PatientRepository repository;
	
	@PostMapping
	@Transactional
	public void register(@RequestBody @Valid RegisterPatientDto dto) {
		repository.save(new Patient(dto));
	}
	
	@GetMapping
	public List<PatientListDto> listAll() {
		return repository.findAll().stream().map(PatientListDto::new).toList();
	}
}

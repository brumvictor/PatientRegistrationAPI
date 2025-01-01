package com.patientregistrationapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.patientregistrationapi.patient.ListPatientDto;
import com.patientregistrationapi.patient.Patient;
import com.patientregistrationapi.patient.PatientDetailsDto;
import com.patientregistrationapi.patient.PatientRepository;
import com.patientregistrationapi.patient.RegisterPatientDto;
import com.patientregistrationapi.patient.UpdatePatientDto;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/patients")
public class PatientController {
	
	@Autowired
	private PatientRepository repository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<PatientDetailsDto> registerPatient(@RequestBody @Valid RegisterPatientDto dto, UriComponentsBuilder uriBuilder) {
		var patient = new Patient(dto);
		repository.save(patient);
		
		var uri = uriBuilder.path("/patient/{id}").buildAndExpand(patient.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new PatientDetailsDto(patient));
	}
	
	@GetMapping
	public ResponseEntity<List<ListPatientDto>> getAllPatients() {
		var list = repository.findAllByActiveTrue().stream().map(ListPatientDto::new).toList();
		
		return ResponseEntity.ok(list);
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity<PatientDetailsDto> updatePatient(@RequestBody @Valid UpdatePatientDto dto) {
		var patient = repository.getReferenceById(dto.id());
		patient.updateInfo(dto);
		
		return ResponseEntity.ok(new PatientDetailsDto(patient));
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Void> deletePatient (@PathVariable Long id) {
		repository.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("deactivate/{id}")
	@Transactional
	public ResponseEntity<Void> deactivatePatient(@PathVariable Long id) {
		var patient = repository.getReferenceById(id);
		patient.deactivatePatient();
		
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("activate/{id}")
	@Transactional
	public ResponseEntity<Void> activatePatient(@PathVariable Long id) {
		var patient = repository.getReferenceById(id);
		patient.activatePatient();
		
		return ResponseEntity.noContent().build();
	}
}

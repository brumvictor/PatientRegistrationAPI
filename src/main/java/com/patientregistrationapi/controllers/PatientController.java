package com.patientregistrationapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patientregistrationapi.patient.ListPatientDto;
import com.patientregistrationapi.patient.Patient;
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
	public void register(@RequestBody @Valid RegisterPatientDto dto) {
		repository.save(new Patient(dto));
	}
	
	@GetMapping
	public List<ListPatientDto> listAll() {
		return repository.findAllByActiveTrue().stream().map(ListPatientDto::new).toList();
	}
	
	@PutMapping
	@Transactional
	public void update(@RequestBody @Valid UpdatePatientDto dto) {
		var patient = repository.getReferenceById(dto.id());
		patient.updateInfo(dto);
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public void delete (@PathVariable Long id) {
		repository.deleteById(id);
	}
	
	@DeleteMapping("deactivate/{id}")
	@Transactional
	public void deactivate(@PathVariable Long id) {
		var patient = repository.getReferenceById(id);
		patient.deactivate();
	}
	
	@PutMapping("activate/{id}")
	@Transactional
	public void activate(@PathVariable Long id) {
		var patient = repository.getReferenceById(id);
		patient.activate();
	}
}

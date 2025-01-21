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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/patients")
@Tag(name = "Patients", description = "Endpoints for patient management")
public class PatientController {
	
	@Autowired
	private PatientRepository repository;
	
	@PostMapping
	@Transactional
	 @Operation(summary = "Register a new patient", description = "Creates a new patient in the database.")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Patient successfully created"),
        @ApiResponse(responseCode = "400", description = "Invalid request data provided"),
        @ApiResponse(responseCode = "403", description = "Unauthorized access"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
	public ResponseEntity<PatientDetailsDto> registerPatient(@RequestBody @Valid RegisterPatientDto dto, UriComponentsBuilder uriBuilder) {
		var patient = new Patient(dto);
		repository.save(patient);
		
		var uri = uriBuilder.path("/patient/{id}").buildAndExpand(patient.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new PatientDetailsDto(patient));
	}
	
	@GetMapping
	 @Operation(summary = "List all active patients", description = "Returns a list of all active patients.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Patient list successfully retrieved"),
        @ApiResponse(responseCode = "403", description = "Unauthorized access"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
	public ResponseEntity<List<ListPatientDto>> getAllPatients() {
		var list = repository.findAllByActiveTrue().stream().map(ListPatientDto::new).toList();
		
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/{id}")
	 @Operation(summary = "Get patient details", description = "Returns the details of a specific patient by ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Patient details successfully retrieved"),
        @ApiResponse(responseCode = "404", description = "Patient not found"),
        @ApiResponse(responseCode = "403", description = "Unauthorized access"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
	public ResponseEntity<PatientDetailsDto> getPatientById (@PathVariable Long id) {
		var patient = repository.getReferenceById(id);
		
		return ResponseEntity.ok(new PatientDetailsDto(patient));
	}
	
	@PutMapping
	@Transactional
	@Operation(summary = "Update patient information", description = "Updates the information of an existing patient.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Patient successfully updated"),
        @ApiResponse(responseCode = "400", description = "Invalid request data provided"),
        @ApiResponse(responseCode = "404", description = "Patient not found"),
        @ApiResponse(responseCode = "403", description = "Unauthorized access"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
	public ResponseEntity<PatientDetailsDto> updatePatient(@RequestBody @Valid UpdatePatientDto dto) {
		var patient = repository.getReferenceById(dto.id());
		patient.updateInfo(dto);
		
		return ResponseEntity.ok(new PatientDetailsDto(patient));
	}
	
	@PutMapping("activate/{id}")
	@Transactional
	@Operation(summary = "Activate a patient", description = "Marks a patient as active in the database.")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Patient successfully activated"),
        @ApiResponse(responseCode = "404", description = "Patient not found"),
        @ApiResponse(responseCode = "403", description = "Unauthorized access"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
	public ResponseEntity<Void> activatePatient(@PathVariable Long id) {
		var patient = repository.getReferenceById(id);
		patient.activatePatient();
		
		return ResponseEntity.noContent().build();
	}
	
	
	@DeleteMapping("deactivate/{id}")
	@Transactional
	@Operation(summary = "Deactivate a patient", description = "Marks a patient as inactive in the database.")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Patient successfully deactivated"),
        @ApiResponse(responseCode = "404", description = "Patient not found"),
        @ApiResponse(responseCode = "403", description = "Unauthorized access"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
	public ResponseEntity<Void> deactivatePatient(@PathVariable Long id) {
		var patient = repository.getReferenceById(id);
		patient.deactivatePatient();
		
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	@Operation(summary = "Delete a patient", description = "Removes a patient from the database by ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Patient successfully deleted"),
        @ApiResponse(responseCode = "404", description = "Patient not found"),
        @ApiResponse(responseCode = "403", description = "Unauthorized access"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
	public ResponseEntity<Void> deletePatient (@PathVariable Long id) {
		repository.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}
}

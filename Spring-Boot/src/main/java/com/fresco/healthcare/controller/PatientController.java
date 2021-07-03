package com.fresco.healthcare.controller;

import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fresco.healthcare.model.Patient;
import com.fresco.healthcare.service.PatientService;

@RestController
@RequestMapping("/patients")
public class PatientController {

	@Autowired
	PatientService patientService;
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody Patient patient) {
		Patient newUser = patientService.register( patient );
		if(newUser != null)
			return new ResponseEntity<>("Registration successfull", HttpStatus.OK);
		else
			return new ResponseEntity<>("Registration failure", HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/list")
	public ResponseEntity<List<Patient>> getAllUser() {
		List<Patient> patients = patientService.getPatients();
		return new ResponseEntity<List<Patient>>(patients,OK);
	}
	
	@GetMapping("/view/{Id}")
	public Patient getPatientDetails(@PathVariable("Id") String id) {
		return patientService.getPatientDetails(id);
	}
	
	@DeleteMapping("/delete/{Id}") 
	public ResponseEntity<String> deletePatient(@PathVariable("Id") String id) {
		patientService.deletePatient(id);
		return new ResponseEntity<String>("Patient Deleted", HttpStatus.OK);
	}
}

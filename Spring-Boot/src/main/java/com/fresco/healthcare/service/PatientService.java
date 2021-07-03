package com.fresco.healthcare.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fresco.healthcare.model.Patient;
import com.fresco.healthcare.repository.PatientRepository;



@Service
public class PatientService {
	@Autowired
	PatientRepository patientRepository;
	
	public Patient register(Patient patient) {
		Optional<Patient> newPatient = patientRepository.findById(patient.getPatientId());
		if(newPatient.isPresent() && newPatient.get()!= null) {
			return null;
		}
		else {
			patientRepository.save(patient);
			return patient;
		}
		
	}

	public List<Patient> getPatients() {
		return patientRepository.findAll();
	}

	public Patient getPatientDetails(String id) {
		Optional<Patient> patient = patientRepository.findById(id);
		if(patient.isPresent() && patient.get() != null) 
			return patient.get();
		else
			return null;
	}

	public void deletePatient(String id) {
		patientRepository.deleteById(id);
		
	}
}

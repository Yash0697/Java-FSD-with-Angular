package com.fresco.healthcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fresco.healthcare.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, String>{

}

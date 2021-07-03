package com.fresco.healthcare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fresco.healthcare.model.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, String> {

	List<Appointment> findByPatientId(String id);

}

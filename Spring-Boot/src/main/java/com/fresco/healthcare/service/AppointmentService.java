package com.fresco.healthcare.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fresco.healthcare.model.Appointment;
import com.fresco.healthcare.repository.AppointmentRepository;



@Service
public class AppointmentService {

	@Autowired
	AppointmentRepository appointmentRepository;
	
	public Appointment register(Appointment appointment) {
		Optional<Appointment> newAppointment = appointmentRepository.findById(appointment.getBookingId());
		if(newAppointment.isPresent() && newAppointment.get()!= null) {
			return null;
		}
		else {
			appointmentRepository.save(appointment);
			return appointment;
		}
		
	}

	public List<Appointment> getAppointments() {
		return appointmentRepository.findAll();
	}

	public Appointment getAppointmentDetails(String id) {
		Optional<Appointment> appointment = appointmentRepository.findById(id);
		if(appointment.isPresent() && appointment.get() != null) 
			return appointment.get();
		else
			return null;
	}

	public void deleteAppointment(String id) {
		appointmentRepository.deleteById(id);
		
	}

	public List<Appointment> getAllAppointmentsOfPatient(String id) {
		List<Appointment> appointments = appointmentRepository.findByPatientId(id);
		return appointments;
	}
}
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

import com.fresco.healthcare.model.Appointment;
import com.fresco.healthcare.service.AppointmentService;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

	@Autowired
	AppointmentService appointmentService;
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody Appointment appointment) {
		Appointment newAppointment = appointmentService.register( appointment );
		if(newAppointment != null)
			return new ResponseEntity<>("Booking successfull", HttpStatus.OK);
		else
			return new ResponseEntity<>("Booking failure", HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/list")
	public ResponseEntity<List<Appointment>> getAllAppointments() {
		List<Appointment> appointments = appointmentService.getAppointments();
		return new ResponseEntity<List<Appointment>>(appointments,OK);
	}
	
	@GetMapping("/view/{appointmentId}")
	public Appointment getPatientDetails(@PathVariable("appointmentId") String id) {
		return appointmentService.getAppointmentDetails(id);
	}
	
	@GetMapping("/list/{patientId}")
	public ResponseEntity<List<Appointment>> getAllAppointmentsOfPatient(@PathVariable("patientId") String id) {
		List<Appointment>appointments = appointmentService.getAllAppointmentsOfPatient(id);
		if(appointments != null && appointments.size() > 0) {
			return new ResponseEntity<List<Appointment>>(appointments, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<List<Appointment>>(appointments, HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/delete/{appointmentId}") 
	public ResponseEntity<String> deleteAppointment(@PathVariable("appointmentId") String id) {
		appointmentService.deleteAppointment(id);
		return new ResponseEntity<String>("Appointment Deleted", HttpStatus.OK);
	}
}

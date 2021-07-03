package com.fresco.healthcare.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Appointment {
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String bookingId;
	private String disease;
	
	private Date tentativeDate;
	private String priority;
	
	private String patientId;

	
	public String getBookingId() {
		return bookingId;
	}

	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}

	public String getDisease() {
		return disease;
	}

	public void setDisease(String disease) {
		this.disease = disease;
	}

	public Date getTentativeDate() {
		return tentativeDate;
	}

	public void setTentativeDate(Date tentativeDate) {
		this.tentativeDate = tentativeDate;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public Appointment(String bookingId, String disease, Date tentativeDate, String priority, String patientId) {
		super();
		this.bookingId = bookingId;
		this.disease = disease;
		this.tentativeDate = tentativeDate;
		this.priority = priority;
		this.patientId = patientId;
	}

	public Appointment() {
		super();
	}
	
	
}

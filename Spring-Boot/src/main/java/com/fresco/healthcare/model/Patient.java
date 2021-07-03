package com.fresco.healthcare.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;



@Entity
public class Patient {
@Id
@GeneratedValue(generator="system-uuid")
@GenericGenerator(name="system-uuid", strategy = "uuid")
private String patientId;
private String patientName;
private String patientEmail;
private String patient_mobile;
private Date registeredDate;
public String getPatientId() {
	return patientId;
}
public void setPatientId(String patientId) {
	this.patientId = patientId;
}
public String getpatientName() {
	return patientName;
}
public void setpatientName(String patientName) {
	this.patientName = patientName;
}
public String getpatientEmail() {
	return patientEmail;
}
public void setpatientEmail(String patientEmail) {
	this.patientEmail = patientEmail;
}
public String getPatient_mobile() {
	return patient_mobile;
}
public void setPatient_mobile(String patient_mobile) {
	this.patient_mobile = patient_mobile;
}
public Date getRegisteredDate() {
	return registeredDate;
}
public void setRegisteredDate(Date registeredDate) {
	this.registeredDate = registeredDate;
}
public Patient( String patientName, String patientEmail, String patient_mobile) {
	super();
	
	this.patientName = patientName;
	this.patientEmail = patientEmail;
	this.patient_mobile = patient_mobile;
	//this.registeredDate = registeredDate;
}
public Patient() {
	super();
}
}
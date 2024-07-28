package com.asm3.prj321.doctorcare.dto;

public class AppointmentRequest {
	private int patientId;
	private int scheduleId;
	
	public AppointmentRequest(int patientId, int scheduleId) {
		super();
		this.patientId = patientId;
		this.scheduleId = scheduleId;
	}
	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public int getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}

	public AppointmentRequest() {
	}

}

package com.asm3.prj321.doctorcare.dto;

public class AppointmentStatusRequest {
	private int appointmentId;
	private int statusId;
	private String reasonForCancel;

	public String getReasonForCancel() {
		return reasonForCancel;
	}

	public void setReasonForCancel(String reasonForCancel) {
		this.reasonForCancel = reasonForCancel;
	}

	public int getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public AppointmentStatusRequest(int appointmentId, int statusId) {
		super();
		this.appointmentId = appointmentId;
		this.statusId = statusId;
	}

	public AppointmentStatusRequest() {
	}

}

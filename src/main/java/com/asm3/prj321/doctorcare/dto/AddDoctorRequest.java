package com.asm3.prj321.doctorcare.dto;

import java.util.List;

import com.asm3.prj321.doctorcare.entities.DoctorUser;
import com.asm3.prj321.doctorcare.entities.User;

public class AddDoctorRequest {
	private User doctor;
	private List<DoctorUser> doctorUsers;

	public User getDoctor() {
		return doctor;
	}

	public void setDoctor(User doctor) {
		this.doctor = doctor;
	}

	public List<DoctorUser> getDoctorUsers() {
		return doctorUsers;
	}

	public void setDoctorUsers(List<DoctorUser> doctorUsers) {
		this.doctorUsers = doctorUsers;
	}

	public AddDoctorRequest() {
	}

}

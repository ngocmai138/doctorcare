package com.asm3.prj321.doctorcare.service;

import java.util.List;
import java.util.Optional;

import com.asm3.prj321.doctorcare.entities.Appointment;
import com.asm3.prj321.doctorcare.entities.User;

public interface AppointmentService {
	public Appointment save(Appointment appointment);
	public Optional<Appointment> findById(int appointmentId);
}

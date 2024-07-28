package com.asm3.prj321.doctorcare.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asm3.prj321.doctorcare.entities.Appointment;
import com.asm3.prj321.doctorcare.repository.AppointmentRepository;
@Service
@Transactional
public class AppointmentServiceImpl implements AppointmentService {
	@Autowired
	private AppointmentRepository appointmentRepository;
	
	@Override
	public Appointment save(Appointment appointment) {
		return appointmentRepository.save(appointment);
	}

	@Override
	public Optional<Appointment> findById(int appointmentId) {
		return appointmentRepository.findById(appointmentId);
	}

}

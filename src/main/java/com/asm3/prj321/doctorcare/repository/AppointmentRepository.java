package com.asm3.prj321.doctorcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asm3.prj321.doctorcare.entities.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
}

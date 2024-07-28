package com.asm3.prj321.doctorcare.service;

import java.util.Optional;

import com.asm3.prj321.doctorcare.entities.Schedule;

public interface ScheduleService {
	public Optional<Schedule> findById(int scheduleId);
}

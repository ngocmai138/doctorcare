package com.asm3.prj321.doctorcare.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asm3.prj321.doctorcare.entities.Schedule;
import com.asm3.prj321.doctorcare.repository.ScheduleRepository;

@Service
@Transactional
public class ScheduleServiceImpl implements ScheduleService {
	@Autowired
	private ScheduleRepository scheduleRepository;

	@Override
	public Optional<Schedule> findById(int scheduleId) {
		return scheduleRepository.findById(scheduleId);
	}

}

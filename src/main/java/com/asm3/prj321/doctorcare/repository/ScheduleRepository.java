package com.asm3.prj321.doctorcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asm3.prj321.doctorcare.entities.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {

}

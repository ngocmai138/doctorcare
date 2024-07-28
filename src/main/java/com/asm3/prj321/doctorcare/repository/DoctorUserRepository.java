package com.asm3.prj321.doctorcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asm3.prj321.doctorcare.entities.DoctorUser;

public interface DoctorUserRepository extends JpaRepository<DoctorUser, Integer> {

}

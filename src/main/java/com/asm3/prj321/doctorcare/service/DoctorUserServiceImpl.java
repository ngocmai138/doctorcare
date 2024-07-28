package com.asm3.prj321.doctorcare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asm3.prj321.doctorcare.entities.DoctorUser;
import com.asm3.prj321.doctorcare.repository.DoctorUserRepository;
@Service
@Transactional
public class DoctorUserServiceImpl implements DoctorUserService {
	@Autowired
	DoctorUserRepository doctorUserRepository;

	@Override
	public DoctorUser save(DoctorUser doctorUser) {
		return doctorUserRepository.save(doctorUser);
	}

}

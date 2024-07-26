package com.asm3.prj321.doctorcare.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asm3.prj321.doctorcare.entities.Clinic;
import com.asm3.prj321.doctorcare.repository.ClinicRepository;

@Service
@Transactional
public class ClinicServiceImpl implements ClinicService {

	@Autowired
	private ClinicRepository clinicRepository;
	
	@Override
	public List<Clinic> getHighlightedClinics(int limitCount) {
		return clinicRepository.getHighlightedClinics(limitCount);
	}

	@Override
	public List<Clinic> seachWithKeyword(String region, String specialization, String price, String clinicName) {
		return clinicRepository.seachWithKeyword(region, specialization, price, clinicName);
	}

}

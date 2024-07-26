package com.asm3.prj321.doctorcare.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asm3.prj321.doctorcare.entities.Specialization;
import com.asm3.prj321.doctorcare.repository.SpecializationRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class SpecializationServiceImpl implements SpecializationService {
	@Autowired
	private SpecializationRepository specializationRepository;
	@Override
	public List<Specialization> getHighlightedSpecializations(int limitCount) {
		return specializationRepository.findHighlightedSpecializations(limitCount);
	}

}

package com.asm3.prj321.doctorcare.service;

import java.util.List;

import com.asm3.prj321.doctorcare.entities.Specialization;

public interface SpecializationService {
	public List<Specialization> findHighlightedSpecializations(int limitCount);
}

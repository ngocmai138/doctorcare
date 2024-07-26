package com.asm3.prj321.doctorcare.service;

import java.util.List;

import com.asm3.prj321.doctorcare.entities.Clinic;

public interface ClinicService {
	public List<Clinic> getHighlightedClinics(int limitCount);
	public List<Clinic> seachWithKeyword(String region, String specialization, String price, String clinicName);
}

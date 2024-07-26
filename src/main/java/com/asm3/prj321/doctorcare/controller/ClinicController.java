package com.asm3.prj321.doctorcare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.asm3.prj321.doctorcare.dto.ApiResponse;
import com.asm3.prj321.doctorcare.entities.Clinic;
import com.asm3.prj321.doctorcare.exception.DoctorCareNotFoundException;
import com.asm3.prj321.doctorcare.globalHandler.ApiResponseHandler;
import com.asm3.prj321.doctorcare.service.ClinicService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/clinic")
@Tag(name = "Clinic Controller", description = "APIs for Clinic")
public class ClinicController {
	@Autowired
	private ClinicService clinicService;
	
	@Autowired
	private ApiResponseHandler apiResponseHandler;
	
	@Operation(summary = "Get highlighted Clinics")
	@GetMapping("/highlighted")
	public ResponseEntity<ApiResponse<List<Clinic>>> getHighLightedClinics(@RequestParam(defaultValue = "4") int limit){
		try {
		List<Clinic> clinics = clinicService.getHighlightedClinics(limit);
		return apiResponseHandler.createSuccessResponse(clinics);
		}catch (Exception e) {
			throw new DoctorCareNotFoundException(e);
		}
	}
	
	@Operation(summary = "Search clinic")
	@GetMapping("/search")
	public ResponseEntity<ApiResponse<List<Clinic>>> searchClinic(
									@RequestParam(value="region", required = false) String region,
									@RequestParam(value = "specialization", required = false) String specialization,
									@RequestParam(value = "priceRange", required = false) String priceRange,
									@RequestParam(value = "clinicName", required = false) String clinicName){
		try {
		List<Clinic> clinics = clinicService.seachWithKeyword(region, specialization, priceRange, clinicName);
		return apiResponseHandler.createSuccessResponse(clinics);}
		catch (Exception e) {
			throw new DoctorCareNotFoundException(e);
		}
	}
}

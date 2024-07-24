package com.asm3.prj321.doctorcare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.asm3.prj321.doctorcare.entities.Specialization;
import com.asm3.prj321.doctorcare.exception.DoctorCareErrorResponse;
import com.asm3.prj321.doctorcare.exception.DoctorCareNotFoundException;
import com.asm3.prj321.doctorcare.service.SpecializationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/specialization")
@Tag(name = "Specialization Controller", description = "APIs for Specialization")
public class SpecializationController {
	@Autowired
	private SpecializationService specializationService;
	
	@Operation(summary = "Get highlighted specializations")
	@GetMapping("/highlighted")
	public ResponseEntity<?> getHighlightedSpecializations(@RequestParam(defaultValue = "4") int limit){
		try {
		List<Specialization> specializations = specializationService.findHighlightedSpecializations(limit);
		return ResponseEntity.ok().body(specializations);}
		catch (Exception e) {
			throw new DoctorCareNotFoundException("Error: "+e);
		}
	}
	
}

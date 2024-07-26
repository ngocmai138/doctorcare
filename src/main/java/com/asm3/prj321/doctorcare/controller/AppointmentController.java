package com.asm3.prj321.doctorcare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asm3.prj321.doctorcare.dto.ApiResponse;
import com.asm3.prj321.doctorcare.entities.Appointment;
import com.asm3.prj321.doctorcare.exception.DoctorCareNotFoundException;
import com.asm3.prj321.doctorcare.globalHandler.ApiResponseHandler;
import com.asm3.prj321.doctorcare.service.AppointmentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/appointment")
@Tag(name = "Appointment Controller", description = "APIs for Appointment")
public class AppointmentController {
	@Autowired
	public ApiResponseHandler apiResponseHandler;
	@Autowired
	public AppointmentService appointmentService;

	@Operation(summary = "Book appointment")
	@PostMapping("/booking")
	public ResponseEntity<ApiResponse<Appointment>> bookAppointment(@RequestBody Appointment appointment) {
		try {
			Appointment savedAppointment = appointmentService.save(appointment);
			return apiResponseHandler.createSuccessResponse("Appointment booked successfully", savedAppointment);
		} catch (Exception e) {
			throw new DoctorCareNotFoundException(e);
		}
	}
}

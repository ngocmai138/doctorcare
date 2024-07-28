package com.asm3.prj321.doctorcare.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asm3.prj321.doctorcare.dto.ApiResponse;
import com.asm3.prj321.doctorcare.dto.AppointmentRequest;
import com.asm3.prj321.doctorcare.entities.Appointment;
import com.asm3.prj321.doctorcare.entities.Schedule;
import com.asm3.prj321.doctorcare.entities.Status;
import com.asm3.prj321.doctorcare.entities.User;
import com.asm3.prj321.doctorcare.exception.DoctorCareNotFoundException;
import com.asm3.prj321.doctorcare.globalHandler.ApiResponseHandler;
import com.asm3.prj321.doctorcare.service.AppointmentService;
import com.asm3.prj321.doctorcare.service.ScheduleService;
import com.asm3.prj321.doctorcare.service.StatusService;
import com.asm3.prj321.doctorcare.service.UserService;

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
	@Autowired
	public UserService userService;
	@Autowired
	public ScheduleService scheduleService;
	@Autowired
	StatusService statusService;

	@Operation(summary = "Book appointment")
	@PostMapping("/booking")
	public ResponseEntity<ApiResponse<Appointment>> bookAppointment(@RequestBody AppointmentRequest request) {
		try {
			Optional<User> user = userService.findById(request.getPatientId());
			User patient = user.get();
			Optional<Schedule> scheduleOption = scheduleService.findById(request.getScheduleId());
			Schedule schedule = scheduleOption.get();
			Status status = statusService.findById(1).get();
			Appointment appointment = new Appointment();
			appointment.setPatient(patient);
			appointment.setSchedule(schedule);
			appointment.setStatus(status);
			Appointment savedAppointment = appointmentService.save(appointment);
			return apiResponseHandler.createSuccessResponse("Appointment booked successfully", savedAppointment);
		} catch (Exception e) {
			throw new DoctorCareNotFoundException(e);
		}
	}
}

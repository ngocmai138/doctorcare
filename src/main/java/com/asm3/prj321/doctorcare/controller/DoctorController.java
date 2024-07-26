package com.asm3.prj321.doctorcare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asm3.prj321.doctorcare.dto.ApiResponse;
import com.asm3.prj321.doctorcare.entities.User;
import com.asm3.prj321.doctorcare.exception.DoctorCareNotFoundException;
import com.asm3.prj321.doctorcare.globalHandler.ApiResponseHandler;
import com.asm3.prj321.doctorcare.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/doctor")
@Tag(name = "Doctor Controller", description = "APIs for only Doctor")
public class DoctorController {
	@Autowired
	private UserService userService;

	@Autowired
	private ApiResponseHandler apiResponseHandler;

	@Operation(summary = "List all patients")
	@GetMapping("/patients")
	public ResponseEntity<ApiResponse<List<User>>> listPatients() {
		try {
			List<User> users = userService.findAllPatients();
			return apiResponseHandler.createSuccessResponse(users);
		} catch (Exception e) {
			throw new DoctorCareNotFoundException(e);
		}
	}
}

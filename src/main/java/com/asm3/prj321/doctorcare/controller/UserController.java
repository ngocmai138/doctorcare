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
import com.asm3.prj321.doctorcare.entities.User;
import com.asm3.prj321.doctorcare.exception.DoctorCareNotFoundException;
import com.asm3.prj321.doctorcare.globalHandler.ApiResponseHandler;
import com.asm3.prj321.doctorcare.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/user")
@Tag(name = "User Controller", description = "APIs for Users")
public class UserController {
	@Autowired
	private UserService userService;

	@Autowired
	private ApiResponseHandler apiResponseHandler;

	@Operation(summary = "5.1.6./ 5.2.1./ 5.3.1. Get personal information with appointments")
	@GetMapping("/personalInfo/{userId}")
	public ResponseEntity<ApiResponse<List<Object[]>>> getPersonalInfo(@PathVariable(value = "userId") int userId) {
		try {
			List<Object[]> personalInfo = userService.getPersonalInfo(userId);
			return apiResponseHandler.createSuccessResponse("Personal Info retrieved successfully",personalInfo);
		} catch (Exception e) {
			throw new DoctorCareNotFoundException(e);
		}
	}

	@Operation(summary = "5.1.8. Search doctors by specialization's name")
	@GetMapping("/search")
	public ResponseEntity<ApiResponse<List<User>>> searchUserBySpecialization(@RequestParam("keyword") String keyword) {
		try {
			System.out.println("Keyword: "+keyword);
			List<User> doctors = userService.searchBySpecialization(keyword);
			if(doctors.size()==0) return apiResponseHandler.createErrorResponse("0 Result");
			return apiResponseHandler.createSuccessResponse("Doctors retrieved successfully", doctors);
		} catch (Exception e) {
			throw new DoctorCareNotFoundException(e);
		}
		
	}
}

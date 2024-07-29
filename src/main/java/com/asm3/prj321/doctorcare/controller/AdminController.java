package com.asm3.prj321.doctorcare.controller;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.asm3.prj321.doctorcare.dto.AddDoctorRequest;
import com.asm3.prj321.doctorcare.dto.ApiResponse;
import com.asm3.prj321.doctorcare.dto.BlockUserRequest;
import com.asm3.prj321.doctorcare.entities.Appointment;
import com.asm3.prj321.doctorcare.entities.DoctorUser;
import com.asm3.prj321.doctorcare.entities.User;
import com.asm3.prj321.doctorcare.exception.DoctorCareNotFoundException;
import com.asm3.prj321.doctorcare.globalHandler.ApiResponseHandler;
import com.asm3.prj321.doctorcare.service.DoctorUserService;
import com.asm3.prj321.doctorcare.service.SendMailService;
import com.asm3.prj321.doctorcare.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/admin")
@Tag(name = "Admin Controller", description = "APIs for Admin")
public class AdminController {
	@Autowired
	private UserService userService;
	@Autowired
	private DoctorUserService doctorUserService;
	@Autowired
	private SendMailService sendMailService;
	@Autowired
	private ApiResponseHandler apiResponseHandler;

	@Operation(summary = "5.3.2. /5.3.5. Block or unblock User/Doctor")
	@PutMapping("/blockuser")
	public ResponseEntity<ApiResponse<User>> changeStatusUser(@RequestBody BlockUserRequest request) {
		try {
			User user = userService.findById(request.getUserId()).get();
			user.setIsActive(request.getIsActive());
			user.setReasonForBlock(request.getReasonForBlock());
			return apiResponseHandler.createSuccessResponse(userService.save(user));
		} catch (Exception e) {
			throw new DoctorCareNotFoundException(e);
		}
	}

	@Operation(summary = "5.3.4. Add new doctor with extra informations")
	@PostMapping("/addDoctor")
	public ResponseEntity<ApiResponse<User>> saveDoctor(@RequestBody AddDoctorRequest request) {
		try {
			User doctor = userService.save(request.getDoctor());
			for (DoctorUser doctorUser : request.getDoctorUsers()) {
				doctorUser.setDoctor(doctor);
				doctorUserService.save(doctorUser);
			}
			return apiResponseHandler.createSuccessResponse(doctor);
		} catch (Exception e) {
			throw new DoctorCareNotFoundException(e);
		}
	}

	@Operation(summary = "6.1. Send mail with medical report to patient")
	@PostMapping("/sendMail")
	public ResponseEntity<ApiResponse<String>> sendMailToPatient(
								@RequestParam("patientId") int patientId,
								@RequestParam("file") MultipartFile file,
								HttpServletRequest servletRequest) {
		try {
			User patient = userService.findById(patientId).get();
			File pdfFile = new File(System.getProperty("java.io.tmpdir")+"/"+file.getOriginalFilename());
			file.transferTo(pdfFile);
			sendMailService.sendMail(patient, servletRequest, pdfFile);
			pdfFile.delete();
			
			return apiResponseHandler.createSuccessResponse("Mail sent successfully!");
		} catch (Exception e) {
			throw new DoctorCareNotFoundException(e);
		}
	}
	
	@Operation(summary = "6.2./ 6.3. Detail appointments of a patient/doctor")
	@GetMapping("/appointments/{userId}")
	public ResponseEntity<ApiResponse<List<Appointment>>> getPatientAppointments(@PathVariable("userId") int userId){
		try {
			User user = userService.findById(userId).get();
			List<Appointment> appointments = user.getAppointments();
			return apiResponseHandler.createSuccessResponse(appointments);
		}catch (Exception e) {
			throw new DoctorCareNotFoundException(e);
		}
	}
}

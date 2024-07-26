package com.asm3.prj321.doctorcare.globalHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.asm3.prj321.doctorcare.dto.DoctorCareErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler
	public ResponseEntity<DoctorCareErrorResponse> handleException(Exception e){
		DoctorCareErrorResponse error = new DoctorCareErrorResponse();
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage(e.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
}

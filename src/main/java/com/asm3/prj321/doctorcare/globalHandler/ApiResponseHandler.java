package com.asm3.prj321.doctorcare.globalHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.asm3.prj321.doctorcare.dto.ApiResponse;

@Component
public class ApiResponseHandler {
	public <T> ResponseEntity<ApiResponse<T>> createResponse(HttpStatus status, String message, T data){
		ApiResponse<T> response = new ApiResponse<T>();
		response.setStatus(status.value());
		response.setMessage(message);
		response.setData(data);
		
		return new ResponseEntity<ApiResponse<T>>(response, status);
	}
	
	public <T> ResponseEntity<ApiResponse<T>> createSuccessResponse(T data){
		return createResponse(HttpStatus.OK, "Successful", data);
	}
	public <T> ResponseEntity<ApiResponse<T>> createSuccessResponse(String message, T data){
		return createResponse(HttpStatus.OK, message, data);
	}
	
	public <T> ResponseEntity<ApiResponse<T>> createErrorResponse(String message){
		return createResponse(HttpStatus.OK, message, null);
	}
}

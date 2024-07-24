package com.asm3.prj321.doctorcare.exception;

public class DoctorCareNotFoundException extends RuntimeException {

	public DoctorCareNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public DoctorCareNotFoundException(String message) {
		super(message);
	}

	public DoctorCareNotFoundException(Throwable cause) {
		super(cause);
	}

}

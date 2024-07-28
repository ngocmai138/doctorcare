package com.asm3.prj321.doctorcare.dto;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;


public class SendMailRequest {
	private int patientId;
	private MultipartFile pdfFile;

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public MultipartFile getPdfFile() {
		return pdfFile;
	}

	public void setPdfFile(MultipartFile pdfFile) {
		this.pdfFile = pdfFile;
	}

	public SendMailRequest() {
	}

}

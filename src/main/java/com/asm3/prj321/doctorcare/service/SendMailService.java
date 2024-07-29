package com.asm3.prj321.doctorcare.service;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import com.asm3.prj321.doctorcare.entities.User;
import com.asm3.prj321.doctorcare.exception.DoctorCareNotFoundException;

import jakarta.servlet.http.HttpServletRequest;
@Service
public class SendMailService {
	private Logger logger = LoggerFactory.getLogger(SendMailService.class);
	@Autowired
	private JavaMailSender javaMailSender;
	

	public void sendMail(User user, HttpServletRequest request, File pdfFile) {
		try {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				System.out.println("user's email: "+user.getEmail());
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);
				message.setTo(user.getEmail());
				message.setSubject("Medical information");
				
				String emailContent = "Hello, Here is your Medical information file.";
				message.setText(emailContent, false);
				
				FileSystemResource file = new FileSystemResource(pdfFile);
				message.addAttachment("MedicalReport.pdf", file);
			}
		};
		this.javaMailSender.send(preparator);
		}catch (Exception e) {
			logger.error("Failed to send email to {}", user.getEmail(), e);
			throw new DoctorCareNotFoundException(e);
		}
	}
}

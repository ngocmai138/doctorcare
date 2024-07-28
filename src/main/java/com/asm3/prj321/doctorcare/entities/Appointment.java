package com.asm3.prj321.doctorcare.entities;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;

import com.asm3.prj321.doctorcare.service.StatusService;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "appointment")
public class Appointment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@ManyToOne()
	@JoinColumn(name = "scheduleId")
	private Schedule schedule;
	@ManyToOne
	@JoinColumn(name = "patientId")
	private User patient;
	@ManyToOne()
	@JoinColumn(name = "statusId")
	private Status status;
	@Column(name = "reasonForCancellation")
	private String reasonForCancellation;
	@Column(name="createdAt")
	private LocalDateTime createdAt;
	@Column(name="updatedAt")
	private LocalDateTime updatedAt;
	@Column(name="deletedAt")
	private LocalDateTime deletedAt;
	
	@PrePersist
	public void onCreate() {
		this.createdAt = LocalDateTime.now();
	}

	public Appointment(Schedule schedule, User patient, Status status, String reasonForCancellation,
			LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt) {
		super();
		this.schedule = schedule;
		this.patient = patient;
		this.status = status;
		this.reasonForCancellation = reasonForCancellation;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.deletedAt = deletedAt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public User getPatient() {
		return patient;
	}

	public void setPatient(User patient) {
		this.patient = patient;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getReasonForCancellation() {
		return reasonForCancellation;
	}

	public void setReasonForCancellation(String reasonForCancellation) {
		this.reasonForCancellation = reasonForCancellation;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public LocalDateTime getDeletedAt() {
		return deletedAt;
	}

	public void setDeletedAt(LocalDateTime deletedAt) {
		this.deletedAt = deletedAt;
	}

	public Appointment() {
	}

}

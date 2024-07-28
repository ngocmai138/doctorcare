package com.asm3.prj321.doctorcare.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="doctor_users")
public class DoctorUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="summary")
	private String summary;
	@Column(name="trainingProcess")
	private String trainingProcess;
	@Column(name="achievement")
	private String achievement;
	@Column(name="createdAt")
	private LocalDateTime createdAt;
	@Column(name="updatedAt")
	private LocalDateTime updatedAt;
	@Column(name="deletedAt")
	private LocalDateTime deletedAt;
	@ManyToOne()
	@JoinColumn(name="doctorId")
	private User doctor;
	@ManyToOne()
	@JoinColumn(name="clinicId")
	private Clinic clinic;
	@ManyToOne()
	@JoinColumn(name="specializationId")
	private Specialization specialization;

	@Override
	public String toString() {
		return "DoctorUser [id=" + id + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", deletedAt="
				+ deletedAt + ", doctor=" + doctor + ", clinic=" + clinic + ", specialization=" + specialization + "]";
	}

	public DoctorUser(LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt, User doctor,
			Clinic clinic, Specialization specialization) {
		super();
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.deletedAt = deletedAt;
		this.doctor = doctor;
		this.clinic = clinic;
		this.specialization = specialization;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getTrainingProcess() {
		return trainingProcess;
	}

	public void setTrainingProcess(String trainingProcess) {
		this.trainingProcess = trainingProcess;
	}

	public String getAchievement() {
		return achievement;
	}

	public void setAchievement(String achievement) {
		this.achievement = achievement;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public User getDoctor() {
		return doctor;
	}

	public void setDoctor(User doctor) {
		this.doctor = doctor;
	}

	public Clinic getClinic() {
		return clinic;
	}

	public void setClinic(Clinic clinic) {
		this.clinic = clinic;
	}

	public Specialization getSpecialization() {
		return specialization;
	}

	public void setSpecialization(Specialization specialization) {
		this.specialization = specialization;
	}

	public DoctorUser() {
	}

}

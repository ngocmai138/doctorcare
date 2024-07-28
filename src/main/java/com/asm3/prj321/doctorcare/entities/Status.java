package com.asm3.prj321.doctorcare.entities;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name="statuses")
public class Status {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="name")
	private String name;
	@Column(name="createdAt")
	private LocalDateTime createdAt;
	@Column(name="updatedAt")
	private LocalDateTime updatedAt;
	@Column(name="deletedAt")
	private LocalDateTime deletedAt;
	@OneToMany(mappedBy = "status")
	@JsonIgnore
	private List<Appointment> appointments;
	@OneToMany(mappedBy = "status",
				fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Patient> patients;
	
	@PrePersist
	public void onCreate() {
		this.createdAt = LocalDateTime.now();
	}
	
	public List<Patient> getPatients() {
		return patients;
	}

	public List<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}

	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Status(String name, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt) {
		super();
		this.name = name;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.deletedAt = deletedAt;
	}

	@Override
	public String toString() {
		return "Status [id=" + id + ", name=" + name + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt
				+ ", deletedAt=" + deletedAt + "]";
	}

	public Status() {
	}

}

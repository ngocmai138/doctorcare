package com.asm3.prj321.doctorcare.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="patients")
public class Patient {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="name")
	private String name;
	@ManyToOne
	@JoinColumn(name="doctorId")
	private User doctor;
	@ManyToOne
	@JoinColumn(name="statusId")
	private Status status;
	@OneToMany(mappedBy = "patient")
	private List<ExtraInfo> extraInfos;
	
	public List<ExtraInfo> getExtraInfos() {
		return extraInfos;
	}

	public void setExtraInfos(List<ExtraInfo> extraInfos) {
		this.extraInfos = extraInfos;
	}

	@Override
	public String toString() {
		return "Patient [id=" + id + ", name=" + name + ", doctor=" + doctor + ", status=" + status + "]";
	}

	public Patient(String name, User doctor, Status status) {
		super();
		this.name = name;
		this.doctor = doctor;
		this.status = status;
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

	public User getDoctor() {
		return doctor;
	}

	public void setDoctor(User doctor) {
		this.doctor = doctor;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Patient() {
	}

}

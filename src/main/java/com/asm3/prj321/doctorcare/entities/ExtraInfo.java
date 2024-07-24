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
@Table(name="extrainfos")
public class ExtraInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="historyBreath")
	private String historyBreath;
	@Column(name="oldForms")
	private String oldForms;
	@Column(name="sendForms")
	private String sendForms;
	@Column(name="moreInfo")
	private String moreInfo;
	@Column(name="createdAt")
	private LocalDateTime createdAt;
	@Column(name="updatedAt")
	private LocalDateTime updatedAt;
	@Column(name="deletedAt")
	private LocalDateTime deletedAt;
	@ManyToOne()
	@JoinColumn(name = "patientId")
	private Patient patient;
	@ManyToOne()
	@JoinColumn(name="placeId")
	private Place place;

	@Override
	public String toString() {
		return "ExtraInfo [id=" + id + ", historyBreath=" + historyBreath + ", oldForms=" + oldForms + ", sendForms="
				+ sendForms + ", moreInfo=" + moreInfo + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt
				+ ", deletedAt=" + deletedAt + ", patient=" + patient + ", place=" + place + "]";
	}

	public ExtraInfo(String historyBreath, String oldForms, String sendForms, String moreInfo, LocalDateTime createdAt,
			LocalDateTime updatedAt, LocalDateTime deletedAt, Patient patient, Place place) {
		super();
		this.historyBreath = historyBreath;
		this.oldForms = oldForms;
		this.sendForms = sendForms;
		this.moreInfo = moreInfo;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.deletedAt = deletedAt;
		this.patient = patient;
		this.place = place;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHistoryBreath() {
		return historyBreath;
	}

	public void setHistoryBreath(String historyBreath) {
		this.historyBreath = historyBreath;
	}

	public String getOldForms() {
		return oldForms;
	}

	public void setOldForms(String oldForms) {
		this.oldForms = oldForms;
	}

	public String getSendForms() {
		return sendForms;
	}

	public void setSendForms(String sendForms) {
		this.sendForms = sendForms;
	}

	public String getMoreInfo() {
		return moreInfo;
	}

	public void setMoreInfo(String moreInfo) {
		this.moreInfo = moreInfo;
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

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Place getPlace() {
		return place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}

	public ExtraInfo() {
	}

}

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
@Table(name="schedules")
public class Schedule {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name="date")
	private String date;
	@Column(name="time")
	private String time;
	@Column(name="maxBooking")
	private String maxBooking;
	@Column(name="sumBooking")
	private String sumBooking;
	@Column(name="createdAt")
	private LocalDateTime createdAt;
	@Column(name="updatedAt")
	private LocalDateTime updatedAt;
	@Column(name="deletedAt")
	private LocalDateTime deletedAt;
	@ManyToOne
	@JoinColumn(name="doctorId")
	private User doctor;

	@Override
	public String toString() {
		return "Schedule [id=" + id + ", date=" + date + ", time=" + time + ", maxBooking=" + maxBooking
				+ ", sumBooking=" + sumBooking + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt
				+ ", deletedAt=" + deletedAt + ", doctor=" + doctor + "]";
	}

	public Schedule(String date, String time, String maxBooking, String sumBooking, LocalDateTime createdAt,
			LocalDateTime updatedAt, LocalDateTime deletedAt, User doctor) {
		super();
		this.date = date;
		this.time = time;
		this.maxBooking = maxBooking;
		this.sumBooking = sumBooking;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.deletedAt = deletedAt;
		this.doctor = doctor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getMaxBooking() {
		return maxBooking;
	}

	public void setMaxBooking(String maxBooking) {
		this.maxBooking = maxBooking;
	}

	public String getSumBooking() {
		return sumBooking;
	}

	public void setSumBooking(String sumBooking) {
		this.sumBooking = sumBooking;
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

	public Schedule() {
	}

}

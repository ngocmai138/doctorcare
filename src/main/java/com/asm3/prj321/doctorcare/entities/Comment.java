package com.asm3.prj321.doctorcare.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="comments")
public class Comment {
	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	@Column(name="timeBooking")
	private String timeBooking;
	@Column(name="dateBooking")
	private String dateBooking;
	@Column(name="name")
	private String name;
	@Column(name="phone")
	private String phone;
	@Column(name="content")
	private String content;
	@Column(name="status")
	private Boolean status;
	@Column(name="createdAt")
	private LocalDateTime createdAt;
	@Column(name="updatedAt")
	private LocalDateTime updatedAt;
	@Column(name="deletedAt")
	private LocalDateTime deletedAt;
	@ManyToOne
	@JoinColumn(name = "doctorId")
	private User doctor;

	@Override
	public String toString() {
		return "Comment [id=" + id + ", timeBooking=" + timeBooking + ", dateBooking=" + dateBooking + ", name=" + name
				+ ", phone=" + phone + ", content=" + content + ", status=" + status + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + ", deletedAt=" + deletedAt + ", doctor=" + doctor + "]";
	}

	public Comment(String timeBooking, String dateBooking, String name, String phone, String content, Boolean status,
			LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt, User doctor) {
		super();
		this.timeBooking = timeBooking;
		this.dateBooking = dateBooking;
		this.name = name;
		this.phone = phone;
		this.content = content;
		this.status = status;
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

	public String getTimeBooking() {
		return timeBooking;
	}

	public void setTimeBooking(String timeBooking) {
		this.timeBooking = timeBooking;
	}

	public String getDateBooking() {
		return dateBooking;
	}

	public void setDateBooking(String dateBooking) {
		this.dateBooking = dateBooking;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
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

	public Comment() {
	}

}

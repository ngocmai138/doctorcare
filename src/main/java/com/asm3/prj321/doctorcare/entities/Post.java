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
@Table(name="posts")
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="title")
	private String title;
	@Column(name="contentMarkdown")
	private String contentMarkdown;
	@Column(name="contentHTML")
	private String contentHTML;
	@Column(name="image")
	private String image;
	@Column(name="confirmByDoctor")
	private Boolean confirmByDoctor;
	@Column(name="createdAt")
	private LocalDateTime createdAt;
	@Column(name="updatedAt")
	private LocalDateTime updatedAt;
	@Column(name="deletedAt")
	private LocalDateTime deletedAt;
	@ManyToOne
	@JoinColumn(name = "forDoctorId")
	private User forDoctor;
	@ManyToOne
	@JoinColumn(name="writerId")
	private User writer;
	@ManyToOne
	@JoinColumn(name="forSpecializationId")
	private Specialization forSpecialization;
	@ManyToOne
	@JoinColumn(name="forClinicId")
	private Clinic forClinic;
	
	@Override
	public String toString() {
		return "Post [id=" + id + ", title=" + title + ", contentMarkdown=" + contentMarkdown + ", contentHTML="
				+ contentHTML + ", image=" + image + ", confirmByDoctor=" + confirmByDoctor + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + ", deletedAt=" + deletedAt + ", forDoctor=" + forDoctor + ", writer="
				+ writer + ", forSpecialization=" + forSpecialization + ", forClinic=" + forClinic + "]";
	}

	public Post(String title, String contentMarkdown, String contentHTML, String image, Boolean confirmByDoctor,
			LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt, User forDoctor, User writer,
			Specialization forSpecialization, Clinic forClinic) {
		super();
		this.title = title;
		this.contentMarkdown = contentMarkdown;
		this.contentHTML = contentHTML;
		this.image = image;
		this.confirmByDoctor = confirmByDoctor;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.deletedAt = deletedAt;
		this.forDoctor = forDoctor;
		this.writer = writer;
		this.forSpecialization = forSpecialization;
		this.forClinic = forClinic;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContentMarkdown() {
		return contentMarkdown;
	}

	public void setContentMarkdown(String contentMarkdown) {
		this.contentMarkdown = contentMarkdown;
	}

	public String getContentHTML() {
		return contentHTML;
	}

	public void setContentHTML(String contentHTML) {
		this.contentHTML = contentHTML;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Boolean getConfirmByDoctor() {
		return confirmByDoctor;
	}

	public void setConfirmByDoctor(Boolean confirmByDoctor) {
		this.confirmByDoctor = confirmByDoctor;
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

	public User getForDoctor() {
		return forDoctor;
	}

	public void setForDoctor(User forDoctor) {
		this.forDoctor = forDoctor;
	}

	public User getWriter() {
		return writer;
	}

	public void setWriter(User writer) {
		this.writer = writer;
	}

	public Specialization getForSpecialization() {
		return forSpecialization;
	}

	public void setForSpecialization(Specialization forSpecialization) {
		this.forSpecialization = forSpecialization;
	}

	public Clinic getForClinic() {
		return forClinic;
	}

	public void setForClinic(Clinic forClinic) {
		this.forClinic = forClinic;
	}

	public Post() {
	}

}

package com.asm3.prj321.doctorcare.entities;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="clinics")
public class Clinic {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="name")
	private String name;
	@Column(name="address")
	private String address;
	@Column(name="phone")
	private String phone;
	@Column(name="introductionHTML")
	private String introductionHTML;
	@Column(name="introductionMarkdown")
	private String introductionMarkdown;
	@Column(name="description")
	private String description;
	@Column(name="image")
	private String image;
	@Column(name="createdAt")
	private LocalDateTime createdAt;
	@Column(name="updatedAt")
	private LocalDateTime updatedAt;
	@Column(name="deletedAt")
	private LocalDateTime deletedAt;
	@OneToMany(mappedBy = "clinic")
	private List<DoctorUser> doctorUsers;
	@OneToMany(mappedBy = "forClinic")
	private List<Post> posts;
	
	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public List<DoctorUser> getDoctorUsers() {
		return doctorUsers;
	}

	public void setDoctorUsers(List<DoctorUser> doctorUsers) {
		this.doctorUsers = doctorUsers;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getIntroductionHTML() {
		return introductionHTML;
	}

	public void setIntroductionHTML(String introductionHTML) {
		this.introductionHTML = introductionHTML;
	}

	public String getIntroductionMarkdown() {
		return introductionMarkdown;
	}

	public void setIntroductionMarkdown(String introductionMarkdown) {
		this.introductionMarkdown = introductionMarkdown;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
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

	@Override
	public String toString() {
		return "Clinic [id=" + id + ", name=" + name + ", address=" + address + ", phone=" + phone
				+ ", introductionHTML=" + introductionHTML + ", introductionMarkdown=" + introductionMarkdown
				+ ", description=" + description + ", image=" + image + ", createdAt=" + createdAt + ", updatedAt="
				+ updatedAt + ", deletedAt=" + deletedAt + "]";
	}

	public Clinic(String name, String address, String phone, String introductionHTML, String introductionMarkdown,
			String description, String image, LocalDateTime createdAt, LocalDateTime updatedAt,
			LocalDateTime deletedAt) {
		super();
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.introductionHTML = introductionHTML;
		this.introductionMarkdown = introductionMarkdown;
		this.description = description;
		this.image = image;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.deletedAt = deletedAt;
	}

	public Clinic() {
	}

}

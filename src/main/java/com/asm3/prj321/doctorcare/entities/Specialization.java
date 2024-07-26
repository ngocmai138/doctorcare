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
import jakarta.persistence.NamedStoredProcedureQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureParameter;
import jakarta.persistence.Table;

@Entity
@Table(name="specializations")
@NamedStoredProcedureQuery(
		name="getHighlightedSpecializations",
		procedureName = "proc_getHighlightedSpecializations",
		resultClasses = Specialization.class,
		parameters = {
				@StoredProcedureParameter(
						name="limitCount",
						mode=ParameterMode.IN,
						type = Integer.class)
		})
public class Specialization {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="name")
	private String name;
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
	@OneToMany(mappedBy = "specialization",
				fetch = FetchType.LAZY)
	@JsonIgnore
	private List<DoctorUser> doctorUsers;
	@OneToMany(mappedBy = "forSpecialization",
				fetch = FetchType.LAZY)
	@JsonIgnore
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

	public Specialization(String name, String description, String image, LocalDateTime createdAt,
			LocalDateTime updatedAt, LocalDateTime deletedAt) {
		super();
		this.name = name;
		this.description = description;
		this.image = image;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.deletedAt = deletedAt;
	}

	@Override
	public String toString() {
		return "Specialization [id=" + id + ", name=" + name + ", description=" + description + ", image=" + image
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", deletedAt=" + deletedAt + "]";
	}

	public Specialization() {
	}

}

package com.asm3.prj321.doctorcare.entities;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="name")
	private String name;
	@Column(name="email")
	private String email;
	@Column(name="password")
	private String password;
	@Column(name="address")
	private String address;
	@Column(name="phone")
	private String phone;
	@Column(name="avatar")
	private String avatar;
	@Column(name="gender")
	private String gender;
	@Column(name="description")
	private String description;
	@Column(name="createdAt")
	private LocalDateTime createdAt;
	@Column(name="updatedAt")
	private LocalDateTime updatedAt;
	@Column(name="deletedAt")
	private LocalDateTime deletedAt;
	@Column(name="isActive")
	private Boolean isActive;
	@ManyToOne
	@JoinColumn(name="roleId")
	@JsonIgnore
	private Role role;
	@OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Schedule> schedules;
	@OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<DoctorUser> doctorUsers;
	@OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Patient> patients;
	@OneToMany(mappedBy = "forDoctor", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Post> postsForDoctor;
	@OneToMany(mappedBy = "writer", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Post> postsByWriter;
	@OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<SupporterLog> supporterLogsForPatient;
	@OneToMany(mappedBy = "supporter", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<SupporterLog> supporterLogsBySupporter;
	@OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Comment> comments;
	@OneToMany(mappedBy = "patient")
	@JsonIgnore
	private List<Appointment> appointments;
	
	@jakarta.persistence.Transient
	private String confirmPassword;
	
	@PrePersist
	public void onCreate() {
		this.isActive = true;
		this.createdAt = LocalDateTime.now();
		Role role = new Role();
		role.setId(2);
		this.setRole(role);
	}
	
	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public Collection<? extends GrantedAuthority> getAuthorities(){
		return List.of(new SimpleGrantedAuthority(role.getName()));
	}
	
	public List<Comment> getComments() {
		return comments;
	}

	public List<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<SupporterLog> getSupporterLogsForPatient() {
		return supporterLogsForPatient;
	}

	public void setSupporterLogsForPatient(List<SupporterLog> supporterLogsForPatient) {
		this.supporterLogsForPatient = supporterLogsForPatient;
	}

	public List<SupporterLog> getSupporterLogsBySupporter() {
		return supporterLogsBySupporter;
	}

	public void setSupporterLogsBySupporter(List<SupporterLog> supporterLogsBySupporter) {
		this.supporterLogsBySupporter = supporterLogsBySupporter;
	}

	public List<Post> getPostsForDoctor() {
		return postsForDoctor;
	}

	public void setPostsForDoctor(List<Post> postsForDoctor) {
		this.postsForDoctor = postsForDoctor;
	}

	public List<Post> getPostsByWriter() {
		return postsByWriter;
	}

	public void setPostsByWriter(List<Post> postsByWriter) {
		this.postsByWriter = postsByWriter;
	}

	public List<Patient> getPatients() {
		return patients;
	}

	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}

	public List<DoctorUser> getDoctorUsers() {
		return doctorUsers;
	}

	public void setDoctorUsers(List<DoctorUser> doctorUsers) {
		this.doctorUsers = doctorUsers;
	}

	public List<Schedule> getSchedules() {
		return schedules;
	}

	public void setSchedules(List<Schedule> schedules) {
		this.schedules = schedules;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", address="
				+ address + ", phone=" + phone + ", avatar=" + avatar + ", gender=" + gender + ", description="
				+ description + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", deletedAt=" + deletedAt
				+ ", isActive=" + isActive + ", role=" + role + "]";
	}

	public User(String name, String email, String password, String address, String phone, String avatar, String gender,
			String description, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt,
			Boolean isActive, Role role) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.address = address;
		this.phone = phone;
		this.avatar = avatar;
		this.gender = gender;
		this.description = description;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.deletedAt = deletedAt;
		this.isActive = isActive;
		this.role = role;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public User() {
	}

}

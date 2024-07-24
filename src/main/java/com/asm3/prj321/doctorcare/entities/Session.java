package com.asm3.prj321.doctorcare.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="session")
public class Session {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="expires")
	private LocalDateTime expire;
	@Column(name="data")
	private String data;
	@Column(name="createdAt")
	private LocalDateTime createdAt;
	@Column(name="updatedAt")
	private LocalDateTime updatedAt;
	
	@Override
	public String toString() {
		return "Session [id=" + id + ", expire=" + expire + ", data=" + data + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + "]";
	}

	public Session(LocalDateTime expire, String data, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.expire = expire;
		this.data = data;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getExpire() {
		return expire;
	}

	public void setExpire(LocalDateTime expire) {
		this.expire = expire;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
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

	public Session() {
	}

}

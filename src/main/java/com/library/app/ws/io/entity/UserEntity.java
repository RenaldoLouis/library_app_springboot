package com.library.app.ws.io.entity;

import java.time.OffsetDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private long id;

	@Column(name = "username", nullable = false)
	private String username;

	@Column(name = "role", nullable = false)
	private String role;

	@Column(name = "email", nullable = false, length = 120)
	private String email;

	@Column(name = "password", nullable = false, length = 50)
	private String password;

	@CreationTimestamp
	@Column(name = "created_at", updatable = false, nullable = false)
	protected OffsetDateTime createdAt = OffsetDateTime.now();

	@Column(name = "is_borrow")
	private Boolean isBorrow;
}

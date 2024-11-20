package com.library.app.ws.io.entity;

import java.time.OffsetDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "borrowed_books")
public class BorrowedBooksEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private long id;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private UserEntity user;

	@ManyToOne
	@JoinColumn(name = "book_id", nullable = false)
	private BooksEntity book;

	@CreationTimestamp
	@Column(name = "created_at", updatable = false, nullable = false)
	protected OffsetDateTime createdAt = OffsetDateTime.now();

	@Column(name = "borrowed_at", updatable = false, nullable = false)
	protected OffsetDateTime borrowedAt = OffsetDateTime.now();

	@Column(name = "returned_at", updatable = false, nullable = false)
	protected OffsetDateTime returnedAt = OffsetDateTime.now();
}

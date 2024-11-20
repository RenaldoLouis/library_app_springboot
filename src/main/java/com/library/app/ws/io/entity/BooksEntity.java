package com.library.app.ws.io.entity;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "books")
public class BooksEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private long id;

	@Column(name = "name", nullable = false)
	private String name;

	@CreationTimestamp
	@Column(name = "created_at", updatable = false, nullable = false)
	protected OffsetDateTime createdAt = OffsetDateTime.now();

	// Add relationship to BorrowedBooksEntity
	@OneToMany(mappedBy = "book")
	private List<BorrowedBooksEntity> borrowedBooks = new ArrayList<>();
}

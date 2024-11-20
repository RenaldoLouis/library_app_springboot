package com.library.app.ws.io.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.app.ws.io.entity.BooksEntity;

@Repository
public interface BooksRepository extends JpaRepository<BooksEntity, Long> {
	BooksEntity findByName(String name);

	BooksEntity findById(Integer bookId);
}

package com.library.app.ws.io.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.library.app.ws.io.entity.BorrowedBooksEntity;

@Repository
public interface BorrowedBooksRepository extends JpaRepository<BorrowedBooksEntity, Long> {
	@Query("SELECT b FROM BorrowedBooksEntity b WHERE b.user.id = :userId AND b.book.id = :bookId AND b.returnedAt IS NULL")
	BorrowedBooksEntity findByUserIdAndBookId(long userId, Integer bookId);
}

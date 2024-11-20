package com.library.app.ws.io.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.app.ws.io.entity.BorrowedBooksEntity;

@Repository
public interface BorrowedBooksRepository extends JpaRepository<BorrowedBooksEntity, Long> {

}

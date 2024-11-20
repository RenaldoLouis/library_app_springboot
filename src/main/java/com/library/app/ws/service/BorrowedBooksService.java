package com.library.app.ws.service;

import java.util.List;

import com.library.app.ws.shared.dto.BorrowedBooksDto;

public interface BorrowedBooksService {

	BorrowedBooksDto createBorrowedBook(BorrowedBooksDto book);

	BorrowedBooksDto returnBorrowedBook(BorrowedBooksDto book);

	List<BorrowedBooksDto> getBorrowedBooks();

}

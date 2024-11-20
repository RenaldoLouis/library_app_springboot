package com.library.app.ws.service;

import com.library.app.ws.shared.dto.BorrowedBooksDto;

public interface BorrowedBooksService {

	BorrowedBooksDto createBorrowedBook(BorrowedBooksDto book);

	BorrowedBooksDto returnBorrowedBook(BorrowedBooksDto book);

}

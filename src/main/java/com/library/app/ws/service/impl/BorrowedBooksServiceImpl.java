package com.library.app.ws.service.impl;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.app.ws.io.entity.BooksEntity;
import com.library.app.ws.io.entity.BorrowedBooksEntity;
import com.library.app.ws.io.entity.UserEntity;
import com.library.app.ws.io.repositories.BooksRepository;
import com.library.app.ws.io.repositories.BorrowedBooksRepository;
import com.library.app.ws.io.repositories.UserRepository;
import com.library.app.ws.service.BorrowedBooksService;
import com.library.app.ws.shared.dto.BorrowedBooksDto;

import jakarta.transaction.Transactional;

@Service
public class BorrowedBooksServiceImpl implements BorrowedBooksService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	BooksRepository booksRepository;

	@Autowired
	BorrowedBooksRepository borrowedBooksRepository;

	@Override
	public BorrowedBooksDto createBorrowedBook(BorrowedBooksDto book) {
		BorrowedBooksDto returnValue = new BorrowedBooksDto();

		OffsetDateTime currentTime = OffsetDateTime.now();

		OffsetDateTime deadlineTime = currentTime.plusDays(book.getBorrowDuration());

		UserEntity selectedUser = userRepository.findById(book.getUser().getId())
				.orElseThrow(() -> new RuntimeException("User not found"));

		if (selectedUser.getIsBorrow()) {
			new RuntimeException("User is already borrowing");
		}

		List<BooksEntity> listBooks = new ArrayList<>();

		for (Integer bookId : book.getBookIds()) {
			BorrowedBooksEntity dataToSave = new BorrowedBooksEntity();
			BooksEntity selectedBooks = booksRepository.findById(bookId);
			listBooks.add(selectedBooks);

			dataToSave.setUser(selectedUser);
			dataToSave.setBook(selectedBooks);
			dataToSave.setDeadline(deadlineTime);
			borrowedBooksRepository.save(dataToSave);
		}

		selectedUser.setIsBorrow(true);

		userRepository.save(selectedUser);

		returnValue.setUser(selectedUser);
		returnValue.setBook(listBooks);
		returnValue.setDeadline(deadlineTime);

		return returnValue;
	}

	@Override
	@Transactional
	public BorrowedBooksDto returnBorrowedBook(BorrowedBooksDto book) {
		BorrowedBooksDto returnValue = new BorrowedBooksDto();

		OffsetDateTime currentTime = OffsetDateTime.now();

		UserEntity selectedUser = userRepository.findById(book.getUser().getId())
				.orElseThrow(() -> new RuntimeException("User not found"));

		List<BooksEntity> listBooks = new ArrayList<>();

		for (Integer bookId : book.getBookIds()) {
			BorrowedBooksEntity dataToSave = borrowedBooksRepository.findByUserIdAndBookId(selectedUser.getId(),
					bookId);
			BooksEntity selectedBooks = booksRepository.findById(bookId);
			listBooks.add(selectedBooks);

			dataToSave.setReturnedAt(currentTime);
			borrowedBooksRepository.save(dataToSave);
		}

		selectedUser.setIsBorrow(false);

		userRepository.save(selectedUser);

		returnValue.setUser(selectedUser);
		returnValue.setBook(listBooks);

		return returnValue;
	}

	@Override
	public List<BorrowedBooksDto> getBorrowedBooks() {
		List<BorrowedBooksDto> returnValue = new ArrayList<>();

		List<BorrowedBooksEntity> borrowedBooksData = borrowedBooksRepository.findAll();

		for (BorrowedBooksEntity eachData : borrowedBooksData) {
			BorrowedBooksDto newData = new BorrowedBooksDto();
			newData.setUser(eachData.getUser());
			newData.setSingleBook(eachData.getBook());

			Boolean isLate = false;

			OffsetDateTime returnTime = eachData.getReturnedAt();
			OffsetDateTime deadlineTime = eachData.getDeadline();

			if (returnTime.isAfter(deadlineTime)) {
				isLate = true; // Set the boolean to true if returnTime is after deadlineTime
			}

			newData.setIsLate(isLate);

			returnValue.add(newData);
		}

		// TODO Auto-generated method stub
		return returnValue;
	}

}

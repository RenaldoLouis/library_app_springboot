package com.library.app.ws.ui.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.app.ws.io.entity.BooksEntity;
import com.library.app.ws.model.request.BorrowedBooksDetailsRequestModel;
import com.library.app.ws.model.request.ReturnedBooksDetailsRequestModel;
import com.library.app.ws.model.response.BorrowBookResp;
import com.library.app.ws.model.response.ErrorMessages;
import com.library.app.ws.service.BorrowedBooksService;
import com.library.app.ws.shared.dto.BorrowedBooksDto;

@RestController
@RequestMapping("/borrowBooks")
public class BorrowedBooksController {

	@Autowired
	BorrowedBooksService borrowedBooksService;

	@PostMapping("/borrow")
	public BorrowBookResp createBorrowBook(@RequestBody BorrowedBooksDetailsRequestModel borrowBookDetails)
			throws Exception {
		BorrowBookResp returnValue = new BorrowBookResp();

		if (borrowBookDetails.getBookIds().size() <= 0 && borrowBookDetails.getUserId() == null)
			throw new RuntimeException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());

		ModelMapper modelMapper = new ModelMapper();
		BorrowedBooksDto borrowedBooksDto = modelMapper.map(borrowBookDetails, BorrowedBooksDto.class);

		BorrowedBooksDto createdBorrowedBook = borrowedBooksService.createBorrowedBook(borrowedBooksDto);

		List<String> booksName = new ArrayList<>();
		for (BooksEntity data : createdBorrowedBook.getBook()) {
			booksName.add(data.getName());
		}

		returnValue.setBookname(booksName);
		returnValue.setDeadline(createdBorrowedBook.getDeadline());
		returnValue.setUsername(createdBorrowedBook.getUser().getUsername());

		return returnValue;
	}

	@PostMapping("/return")
	public BorrowBookResp returnBook(@RequestBody ReturnedBooksDetailsRequestModel borrowBookDetails) throws Exception {
		BorrowBookResp returnValue = new BorrowBookResp();

		if (borrowBookDetails.getBookIds().size() <= 0 && borrowBookDetails.getUserId() == null)
			throw new RuntimeException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());

		ModelMapper modelMapper = new ModelMapper();
		BorrowedBooksDto borrowedBooksDto = modelMapper.map(borrowBookDetails, BorrowedBooksDto.class);

		BorrowedBooksDto createdBorrowedBook = borrowedBooksService.returnBorrowedBook(borrowedBooksDto);

		List<String> booksName = new ArrayList<>();
		for (BooksEntity data : createdBorrowedBook.getBook()) {
			booksName.add(data.getName());
		}

		returnValue.setBookname(booksName);
		returnValue.setDeadline(createdBorrowedBook.getDeadline());
		returnValue.setUsername(createdBorrowedBook.getUser().getUsername());

		return returnValue;
	}

}

package com.library.app.ws.ui.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.app.ws.exceptions.UserServiceException;
import com.library.app.ws.model.request.BooksDetailRequestModel;
import com.library.app.ws.model.response.BooksResp;
import com.library.app.ws.model.response.ErrorMessages;
import com.library.app.ws.service.BooksService;
import com.library.app.ws.shared.dto.BooksDto;

@RestController
@RequestMapping("/books")
public class BooksController {

	@Autowired
	BooksService booksService;

	@PostMapping("/create")
	public BooksResp createUser(@RequestBody BooksDetailRequestModel booksDetails) throws Exception {
		BooksResp returnValue = new BooksResp();

		if (booksDetails.getName().isEmpty())
			throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());

		ModelMapper modelMapper = new ModelMapper();
		BooksDto booksDto = modelMapper.map(booksDetails, BooksDto.class);

		BooksDto createdUser = booksService.createBook(booksDto);
		returnValue = modelMapper.map(createdUser, BooksResp.class);

		return returnValue;
	}
}

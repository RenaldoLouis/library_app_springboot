package com.library.app.ws.service.impl;

import javax.management.RuntimeErrorException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.app.ws.io.entity.BooksEntity;
import com.library.app.ws.io.repositories.BooksRepository;
import com.library.app.ws.service.BooksService;
import com.library.app.ws.shared.dto.BooksDto;

@Service
public class BooksServiceImpl implements BooksService {

	@Autowired
	BooksRepository booksRepository;

	@Override
	public BooksDto createBook(BooksDto book) {

		if (booksRepository.findByName(book.getName()) != null)
			throw new RuntimeErrorException(null, "Record Already exist");

//		BeanUtils.copyProperties(user, userEntity);
		ModelMapper modelMapper = new ModelMapper();
		BooksEntity booksEntity = modelMapper.map(book, BooksEntity.class);

		booksEntity.setName(book.getName());

		BooksEntity storedUserDetails = booksRepository.save(booksEntity);

		BooksDto returnValue = modelMapper.map(storedUserDetails, BooksDto.class);

		return returnValue;
	}

}

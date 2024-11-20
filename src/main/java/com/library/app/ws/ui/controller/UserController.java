package com.library.app.ws.ui.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.app.ws.exceptions.UserServiceException;
import com.library.app.ws.model.request.UserDetailsRequestModel;
import com.library.app.ws.model.response.ErrorMessages;
import com.library.app.ws.model.response.UserResp;
import com.library.app.ws.service.UserService;
import com.library.app.ws.shared.dto.UserDto;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/signUp")
	public UserResp createUser(@RequestBody UserDetailsRequestModel userDetails) throws Exception {
		UserResp returnValue = new UserResp();

		if (userDetails.getUsername().isEmpty())
			throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());

		ModelMapper modelMapper = new ModelMapper();
		UserDto userDto = modelMapper.map(userDetails, UserDto.class);
		userDto.setRole(userDetails.getRole());

		UserDto createdUser = userService.createUser(userDto);
		returnValue = modelMapper.map(createdUser, UserResp.class);

		return returnValue;
	}
}

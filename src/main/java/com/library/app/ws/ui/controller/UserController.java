package com.library.app.ws.ui.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	@GetMapping()
	public List<UserResp> getUsers(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "10") int limit) {

		List<UserResp> returnedValue = new ArrayList<>();

		List<UserDto> userDtos = userService.getUsers(page, limit);

		for (UserDto userDto : userDtos) {
			UserResp userModel = new UserResp();
			BeanUtils.copyProperties(userDto, userModel);
			returnedValue.add(userModel);
		}

		return returnedValue;
	}

	@PostAuthorize("hasRole('ADMIN') or returnObject.userId == principal.userId")
	@GetMapping("/{id}")
	public UserResp getUser(@PathVariable String id) {
		UserResp returnValue = new UserResp();

		UserDto userDto = userService.getUserByUserId(id);
		BeanUtils.copyProperties(userDto, returnValue);

		return returnValue;
	}

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

	@PostMapping
	public String addUser() {
		return "add user called";
	}

	@PutMapping
	public String updateUser() {
		return "update user called";
	}

	@DeleteMapping
	public String deleteUser() {
		return "delete user called";
	}
}

package com.library.app.ws.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.library.app.ws.shared.dto.UserDto;

public interface UserService extends UserDetailsService {
	UserDto createUser(UserDto user);

	UserDto getUser(String email);

	UserDto getUserByUserId(long userId);

	List<UserDto> findUserByFirstName(int page, int limit, String firstName);

	String confirmUser(String userId);

	String updateUserEmailStatus(String userId, Boolean status);

	UserDto updateUser(String userId, UserDto user);

	void deleteUser(String userId);

	List<UserDto> getUsers(int page, int limit);

	List<UserDto> getConfirmedUsers(int page, int limit);

	boolean verifyEmailToken(String token);

	boolean resetPassword(String token, String password);
}

package com.library.app.ws.service.impl;

import javax.management.RuntimeErrorException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.library.app.ws.io.entity.UserEntity;
import com.library.app.ws.io.repositories.UserRepository;
import com.library.app.ws.service.UserService;
import com.library.app.ws.shared.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public UserDto createUser(UserDto user) {

		if (userRepository.findByEmail(user.getEmail()) != null)
			throw new RuntimeErrorException(null, "Record Already exist");

//		BeanUtils.copyProperties(user, userEntity);
		ModelMapper modelMapper = new ModelMapper();
		UserEntity userEntity = modelMapper.map(user, UserEntity.class);

		userEntity.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userEntity.setUsername(user.getUsername());
		userEntity.setRole(user.getRole());
		userEntity.setIsBorrow(false);

		UserEntity storedUserDetails = userRepository.save(userEntity);

//      BeanUtils.copyProperties(storedUserDetails, returnValue);
		UserDto returnValue = modelMapper.map(storedUserDetails, UserDto.class);

		return returnValue;
	}

}

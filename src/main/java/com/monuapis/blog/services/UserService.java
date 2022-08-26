package com.monuapis.blog.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.monuapis.blog.payloads.UserDto;


public interface UserService {
	
	UserDto registerNewUser(UserDto userDto);
	
	UserDto createUser(UserDto user);
	
	UserDto getUserById(Integer userId);
	
	UserDto updateUser(UserDto user,Integer Id);
	
	List<UserDto> getUserAll();
	
	void deleteUser(Integer Id);
}

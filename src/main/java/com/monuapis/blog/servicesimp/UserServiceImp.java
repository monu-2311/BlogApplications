package com.monuapis.blog.servicesimp;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.monuapis.blog.entities.Roles;
import com.monuapis.blog.entities.User;
import com.monuapis.blog.exceptions.ResourceNotFoundException;
import com.monuapis.blog.payloads.UserDto;
import com.monuapis.blog.repositories.RoleRepo;
import com.monuapis.blog.repositories.UserRepo;
import com.monuapis.blog.services.UserService;

@Service
public class UserServiceImp implements UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepo rRepo;
	

	@Override
	public UserDto createUser(UserDto userDto) {
		// TODO Auto-generated method stub
		
		User user = this.dtoToUser(userDto);
		
		User savedUser = this.userRepo.save(user);
		return this.userToDto(savedUser);
	}
	
	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		// TODO Auto-generated method stub
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
		
		user.setName(userDto.getName());
		user.setPassword(userDto.getPassword());
		user.setEmail(userDto.getEmail());
		user.setAbout(userDto.getAbout());
		
		User userUpdate= this.userRepo.save(user);
		UserDto userDto1 = this.userToDto(userUpdate);
		
		return  userDto1;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		// TODO Auto-generated method stub
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
		
		UserDto userDto = this.userToDto(user);
		return userDto;
	}

	@Override
	public List<UserDto> getUserAll() {
		// TODO Auto-generated method stub
		List<User> users = this.userRepo.findAll();
		List<UserDto> userDto = users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		
		return userDto;
	}

	@Override
	public void deleteUser(Integer userId) {
		// TODO Auto-generated method stub
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
		this.userRepo.deleteById(userId);

	}
	
	// use to send data from userDto to the User Entities
	private User dtoToUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
		return user;
	}
	
	
	// use to send data into the UserDto
	public UserDto userToDto(User user) {
		
		//autommatic mapping of object or convert one obj into the another obj
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		
		
		//below we do maping mannual 
		
//		UserDto userDto = new UserDto();
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setPassword(user.getPassword());
//		userDto.setEmail(user.getEmail());
//		userDto.setAbout(user.getAbout());
		
		return userDto;
	}

	@Override
	public UserDto registerNewUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
			
		
		//encode password
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		
		//Set the ROle to new user
		Roles roles=this.rRepo.findById(502).get();
		
		user.getRoles().add(roles);
		
		User saveUser = this.userRepo.save(user);
		return this.modelMapper.map(saveUser, UserDto.class);
	}

}

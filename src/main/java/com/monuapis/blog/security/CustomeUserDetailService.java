package com.monuapis.blog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.monuapis.blog.entities.User;
import com.monuapis.blog.exceptions.ResourceNotFoundException;
import com.monuapis.blog.repositories.UserRepo;


@Service
public class CustomeUserDetailService implements UserDetailsService{

	@Autowired
	private UserRepo userReop;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		 User user = this.userReop.findByEmail(username).orElseThrow(()-> new ResourceNotFoundException("User", "Email:"+username, 0));
		 return user;
	}

}

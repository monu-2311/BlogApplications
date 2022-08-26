package com.monuapis.blog.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	
	private int id;
	
	@NotEmpty
	@Size(min=4, message ="User must be min of 4 Character")
	private String name;
	
	@NotEmpty
	@Size(min=8, max=20, message="Size of password must be 8")
	private String password;
	
	@Email(message="Emaild address is not valid !!")
	@NotEmpty(message= "Plase Enter the Email")
	private String email;
	
	@NotEmpty(message="Please Enter the About")
	private String about;	
	
}

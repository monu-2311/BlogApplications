package com.monuapis.blog.payloads;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class JwtAuthRequest {
	
	private String username;
	
	@NotNull(message="Password can't be null")
	@NotBlank(message="password can't be blank")
	private String password;
}

package com.monuapis.blog.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostDto {
	
	private Integer id;
	
	@NotBlank(message="Please enter the tittle")
	private String tittle;
	
	@NotBlank(message="Please enter the content !!")
	@Size(min=4,max =20000)
	private String content;
	
	
	private String imageName;
	
	private Date addedDate;
	
	private CategoryDto category;

	private UserDto user;
	
	private Set<CommentDto> comments = new HashSet<>();

}

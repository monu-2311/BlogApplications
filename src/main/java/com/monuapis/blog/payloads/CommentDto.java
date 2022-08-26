package com.monuapis.blog.payloads;

import javax.validation.constraints.NotNull;

import com.monuapis.blog.entities.Post;
import com.monuapis.blog.entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class CommentDto {

	private Integer commentId;
	
	@NotNull(message="Comment can't null !!")
	private String content;
	
	private UserDto users;
	
}

package com.monuapis.blog.services;

import com.monuapis.blog.payloads.CommentDto;

public interface CommentService {

	CommentDto createComment(CommentDto commentDto,Integer userId,Integer postId);
	
	void deleteComment(Integer commentId);
}

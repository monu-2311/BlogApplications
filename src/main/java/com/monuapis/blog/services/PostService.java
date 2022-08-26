package com.monuapis.blog.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.monuapis.blog.payloads.PostDto;
import com.monuapis.blog.payloads.PostResponse;


public interface PostService {

	PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
	
	PostDto updatePost(PostDto postDto,Integer postId);
	
	void deletePost(Integer postId);
	
	
	PostDto getByIdPost(Integer postId);
	
	
	PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
	
	List<PostDto> getPostByUser(Integer userId);
	
	List<PostDto> getPostByCategory(Integer categoryId);
	
	List<PostDto> searchPostBykey(String keyword);
	
	
	
}

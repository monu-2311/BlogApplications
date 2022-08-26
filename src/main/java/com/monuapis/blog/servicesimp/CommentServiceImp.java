package com.monuapis.blog.servicesimp;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monuapis.blog.entities.Comment;
import com.monuapis.blog.entities.Post;
import com.monuapis.blog.entities.User;
import com.monuapis.blog.exceptions.ResourceNotFoundException;
import com.monuapis.blog.payloads.CommentDto;
import com.monuapis.blog.repositories.CommentRepo;
import com.monuapis.blog.repositories.PostRepo;
import com.monuapis.blog.repositories.UserRepo;
import com.monuapis.blog.services.CommentService;

@Service
public class CommentServiceImp implements CommentService {
	
	@Autowired
	private CommentRepo cRepo;
	
	@Autowired
	private UserRepo uRepo;
	
	@Autowired
	private PostRepo pRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	
	@Override
	public CommentDto createComment(CommentDto commentDto,Integer userId, Integer postId) {
		// TODO Auto-generated method stub
		
		Post post = this.pRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "PostId", postId));
		User user = this.uRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "UserId", userId));
		
		Comment comment = this.modelMapper.map(commentDto,Comment.class);
		
		comment.setPost(post);
		comment.setUsers(user);
		
		Comment saveComment = this.cRepo.save(comment);
		
		return this.modelMapper.map(saveComment,CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		// TODO Auto-generated method stub
		Comment comment = this.cRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment", "commentID", commentId));
		this.cRepo.delete(comment);;
	}

}

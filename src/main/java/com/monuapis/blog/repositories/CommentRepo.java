package com.monuapis.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.monuapis.blog.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment , Integer>{

	
}

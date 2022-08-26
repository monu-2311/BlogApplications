package com.monuapis.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.monuapis.blog.entities.Category;
import com.monuapis.blog.entities.Post;
import com.monuapis.blog.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer> {
	
	List<Post> 	findByUser(User user);
	List<Post> 	findByCategory(Category category);
	
	List<Post> findByTittleContaining(String tittle);
}

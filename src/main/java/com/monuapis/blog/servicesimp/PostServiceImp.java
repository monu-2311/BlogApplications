package com.monuapis.blog.servicesimp;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.monuapis.blog.entities.Category;
import com.monuapis.blog.entities.Post;
import com.monuapis.blog.entities.User;
import com.monuapis.blog.exceptions.ResourceNotFoundException;
import com.monuapis.blog.payloads.PostDto;
import com.monuapis.blog.payloads.PostResponse;
import com.monuapis.blog.repositories.CategoryRepo;
import com.monuapis.blog.repositories.PostRepo;
import com.monuapis.blog.repositories.UserRepo;
import com.monuapis.blog.services.PostService;

@Service
public class PostServiceImp implements PostService {
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	
	@Autowired
	private ModelMapper modelMaper;
	
	
	
	//CREATE POST
	@Override
	public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {
		// TODO Auto-generated method stub
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
		
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Id", categoryId));
		
		Post post = this.modelMaper.map(postDto,Post.class);
	
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		
		Post savePost  = this.postRepo.save(post);
		return this.modelMaper.map(savePost, PostDto.class);
	}
	
	
	//UPDATE POST
	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		// TODO Auto-generated method stub
		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "PostId", postId));
		
		post.setTittle(postDto.getTittle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		
		
		Post savePost = this.postRepo.save(post);
	
		return this.modelMaper.map(savePost, PostDto.class);
	}

	
	//DELETE POST
	@Override
	public void deletePost(Integer postId) {
		// TODO Auto-generated method stub
		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "PostId", postId));
		this.postRepo.deleteById(postId);
		
	}

	@Override
	public PostDto getByIdPost(Integer postId) {
		// TODO Auto-generated method stub
		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "PostId", postId));
		PostDto postDto = this.modelMaper.map(post, PostDto.class);
		return postDto;
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir) {
		// TODO Auto-generated method stub
		
		Sort sort = (sortDir.equalsIgnoreCase("asc")? Sort.by(sortBy).ascending(): Sort.by(sortBy).descending());
		
		Pageable p = PageRequest.of(pageNumber, pageSize,sort);
		
		Page<Post> pagePost = this.postRepo.findAll(p);
		
		List<Post> posts = pagePost.getContent();
		
		List<PostDto> postDtos = posts.stream().map((post)->this.modelMaper.map(post, PostDto.class)).collect(Collectors.toList());
		
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElement(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		
		return postResponse;
	}

	@Override
	public List<PostDto> getPostByUser(Integer userId) {
		// TODO Auto-generated method stub
		User users = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
		List<Post> posts = this.postRepo.findByUser(users);
		List<PostDto> postDto= posts.stream().map((post)->this.modelMaper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDto;
	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Id", categoryId));
		List<Post> posts = this.postRepo.findByCategory(cat);
		List<PostDto> postDto= posts.stream().map((post)->this.modelMaper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDto;
	}

	@Override
	public List<PostDto> searchPostBykey(String keyword) {
		// TODO Auto-generated method stub
		List<Post>posts  = this.postRepo.findByTittleContaining(keyword);
		
		List<PostDto> postDto = posts.stream().map((post)->this.modelMaper.map(post, PostDto.class)).collect(Collectors.toList());
		
		return postDto;
	}

}

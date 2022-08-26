package com.monuapis.blog.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.monuapis.blog.payloads.ApiResponse;
import com.monuapis.blog.payloads.PostDto;
import com.monuapis.blog.payloads.PostResponse;
import com.monuapis.blog.services.FileService;
import com.monuapis.blog.services.PostService;

@RestController
@RequestMapping("/api/")
public class PostController {
	
	@Autowired
	private PostService postService;
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private String path;
	
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto, @PathVariable Integer userId,@PathVariable Integer categoryId){
		PostDto createPostDto = this.postService.createPost(postDto, userId, categoryId);
		
		return new ResponseEntity<PostDto>(createPostDto,HttpStatus.CREATED);
	}
	
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId){
		List<PostDto> postDto = this.postService.getPostByUser(userId);
		
		return new ResponseEntity<List<PostDto>>(postDto,HttpStatus.OK);
	}
	
	
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId){
		List<PostDto> postDto = this.postService.getPostByCategory(categoryId);
		
		return new ResponseEntity<List<PostDto>>(postDto,HttpStatus.OK);
	}
	
	
	@GetMapping("/post/{postId}")
	public ResponseEntity<PostDto> getSinglePost(@PathVariable Integer postId){
		
		return  ResponseEntity.ok(this.postService.getByIdPost(postId));
	}
	
	
	@GetMapping("/post")
	public ResponseEntity<PostResponse> getAllPost(
			@RequestParam(value="pageNumber",defaultValue = "0",required = false) Integer pageNumber,
			@RequestParam(value="pageSize",defaultValue = "5",required = false) Integer pageSize,
			@RequestParam(value="sortBy",defaultValue = "id",required = false) String sortBy,
			@RequestParam(value="sortDir",defaultValue = "asc",required = false) String sortDir
			){
		
		PostResponse postResponse = this.postService.getAllPost(pageNumber,pageSize,sortBy,sortDir);
		return  new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
	}
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/post/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId){
		this.postService.deletePost(postId);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("User delete Successfully",true),HttpStatus.OK);
	}
	
	@PutMapping("/post/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postdto,@PathVariable Integer postId){
		PostDto updatepostDto = this.postService.updatePost(postdto,postId);
		
		return new ResponseEntity<PostDto>(updatepostDto,HttpStatus.OK);
	}
	
	@GetMapping("/post/search/{keyword}")
	public ResponseEntity<List<PostDto>> searchByKeyword(@PathVariable String keyword){
		List<PostDto> postDto = this.postService.searchPostBykey(keyword);
		
		return new ResponseEntity<List<PostDto>>(postDto,HttpStatus.OK);
	}
	
	//Post Image Upload
	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadImagePhoto(@RequestParam("image") MultipartFile image,@PathVariable Integer postId) throws IOException{
	
		PostDto postdto = this.postService.getByIdPost(postId);
		String fileName =this.fileService.uploadImage(path, image);
		postdto.setImageName(fileName);	
		PostDto updatePostDto = this.postService.updatePost(postdto, postId);
		
		return new ResponseEntity<PostDto>(updatePostDto,HttpStatus.OK);
	}
	
	@GetMapping(value="/post/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable String imageName,HttpServletResponse response) throws IOException {
		InputStream resource = this.fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
	}

}

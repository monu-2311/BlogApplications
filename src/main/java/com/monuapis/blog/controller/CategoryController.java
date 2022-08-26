package com.monuapis.blog.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monuapis.blog.payloads.ApiResponse;
import com.monuapis.blog.payloads.CategoryDto;
import com.monuapis.blog.services.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
		CategoryDto createcategoryDto = this.categoryService.createCategoryDto(categoryDto);
		return new ResponseEntity<CategoryDto>(createcategoryDto,HttpStatus.CREATED);
	}
	
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable Integer categoryId){
		CategoryDto updatecategoryDto = this.categoryService.updateCategoryDto(categoryDto,categoryId);
		return ResponseEntity.ok(updatecategoryDto);
	}
	
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponse> deleteSingleCategory(@Valid @PathVariable Integer categoryId){
		this.categoryService.deleteCategoryDto(categoryId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User delete Successfully",true),HttpStatus.OK);
	}
	
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getSingleCategory(@Valid @PathVariable Integer categoryId){
		return ResponseEntity.ok( this.categoryService.getCategoryDto(categoryId));
	}
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategory(){
		return ResponseEntity.ok( this.categoryService.getAllCategoryDto());
	}
}

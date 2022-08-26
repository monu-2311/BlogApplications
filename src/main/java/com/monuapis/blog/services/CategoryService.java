package com.monuapis.blog.services;

import java.util.List;

import com.monuapis.blog.payloads.CategoryDto;

public interface CategoryService {

	 CategoryDto createCategoryDto(CategoryDto categoryDto);
	
	
	 CategoryDto updateCategoryDto(CategoryDto categoryDto,Integer categoryId);
	
	
	 void deleteCategoryDto(Integer categoryId);
	
	
	 CategoryDto getCategoryDto(Integer categoryId);
	
	
	 List<CategoryDto> getAllCategoryDto();
}

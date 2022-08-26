package com.monuapis.blog.servicesimp;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monuapis.blog.entities.Category;
import com.monuapis.blog.exceptions.ResourceNotFoundException;
import com.monuapis.blog.payloads.CategoryDto;
import com.monuapis.blog.repositories.CategoryRepo;
import com.monuapis.blog.services.CategoryService;

@Service
public class CategoryServiceImp implements CategoryService {
	@Autowired
	private CategoryRepo categoryRepo; 
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategoryDto(CategoryDto categoryDto) {
		// TODO Auto-generated method stub
		Category category = this.modelMapper.map(categoryDto, Category.class);
		
		Category saveCategory = this.categoryRepo.save(category);
		return this.modelMapper.map(saveCategory,CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategoryDto(CategoryDto categoryDto, Integer categoryId) {
		// TODO Auto-generated method stub
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Id", categoryId));
		
		category.setCategoryTittle(categoryDto.getCategoryTittle());
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		
		Category updateCategory = this.categoryRepo.save(category);
		
		return this.modelMapper.map(updateCategory, CategoryDto.class);
	}

	@Override
	public void deleteCategoryDto(Integer categoryId) {
		// TODO Auto-generated method stub
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Id", categoryId));
		this.categoryRepo.deleteById(categoryId);

	}

	@Override
	public CategoryDto getCategoryDto(Integer categoryId) {
		// TODO Auto-generated method stub
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Id", categoryId));
		
		return this.modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategoryDto() {
		// TODO Auto-generated method stub
		List<Category> categoryories = this.categoryRepo.findAll();
		List<CategoryDto> categoryDto = categoryories.stream().map((cat)-> this.modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
		
		return categoryDto;
	}
	

}

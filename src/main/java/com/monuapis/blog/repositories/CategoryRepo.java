package com.monuapis.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.monuapis.blog.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}

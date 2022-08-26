package com.monuapis.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.monuapis.blog.entities.Roles;

public interface RoleRepo extends JpaRepository<Roles, Integer>{

	
}

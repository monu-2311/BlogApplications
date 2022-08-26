package com.monuapis.blog.payloads;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
	
	
	private Integer categoryId;
	
	@NotEmpty(message="Plase Enter the Tittle !!")
	@NotNull(message="Plase Enter The Tittle !!")
	private String categoryTittle;
	
	@NotEmpty(message="Plase Enter the Description")
	@Size(min= 4,max = 250,message = "description must but 4 Characters !!")
	private String categoryDescription;
}

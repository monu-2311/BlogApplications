package com.monuapis.blog.payloads;

import java.util.List;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class PostResponse {
	
	private List<PostDto> content;
	private int pageNumber;
	private int pageSize;
	private long totalElement;
	private long totalPages;	
	private boolean lastPage;

	

}

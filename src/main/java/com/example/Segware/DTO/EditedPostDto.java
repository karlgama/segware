package com.example.Segware.DTO;

import org.hibernate.validator.constraints.Length;

import com.example.Segware.models.Post;
import com.example.Segware.repositories.PostRepository;
import com.sun.istack.NotNull;

public class EditedPostDto {
	@NotNull
	@Length(min = 5)
	private String title;
	@NotNull
	@Length(min = 10)
	private String message;

	public String gettitle() {
		return title;
	}

	public void settitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Post update(Long id, PostRepository postsRepository) {
		Post post= postsRepository.getById(id);
		post.setMessage(this.message);
		post.settitle(this.title);
		return post;
	}

}

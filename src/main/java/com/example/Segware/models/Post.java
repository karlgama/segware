package com.example.Segware.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Value;

import com.sun.istack.NotNull;

@Entity
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Value("${upvotes.key:0}")
	private Integer upvotes;
	@NotNull
	@Length(min = 5)
	private String titulo;
	@NotNull
	@Length(min = 5, max = 1200)
	private String message;

	public Post() {
		this.upvotes = 0;
	}

	public Post(String message, String titulo) {
		this.titulo = titulo;
		this.message = message;
	}

	public Integer getUpvotes() {
		return upvotes;
	}

	public void setUpvotes() {
		this.upvotes += 1;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Long getId() {
		return id;
	}

}
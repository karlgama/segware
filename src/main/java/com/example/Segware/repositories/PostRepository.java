package com.example.Segware.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Segware.models.Post;

public interface PostRepository extends JpaRepository<Post, Long>{

}

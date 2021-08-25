package com.example.Segware.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.Segware.DTO.EditedPostDto;
import com.example.Segware.models.Post;
import com.example.Segware.repositories.PostRepository;

@RestController
@RequestMapping("/posts")
public class PostsController {

	@Autowired
	private PostRepository postRepository;

	@GetMapping
	public List<Post> index(
			@PageableDefault(sort = "id", direction = Direction.DESC, page = 0, size = 10) Pageable paginacao) {
		List<Post> posts = postRepository.findAll();
		return posts;

	}

	@GetMapping("/{id}")
	public ResponseEntity<Post> postDetail(@PathVariable Long id) {
		Optional<Post> post = postRepository.findById(id);
		if (post.isPresent()) {
			return ResponseEntity.ok(post.get());
		}
		return ResponseEntity.notFound().build();

	}

	@PostMapping
	@Transactional
	public ResponseEntity<Post> create(@RequestBody Post post, UriComponentsBuilder uriBuilder) {
		postRepository.save(post);
		URI uri = uriBuilder.path("/posts/{id}").buildAndExpand(post.getId()).toUri();
		return ResponseEntity.created(uri).body(post);
	}

	@PatchMapping("/{id}")
	@Transactional
	public ResponseEntity<Post> editPost(@PathVariable Long id, @RequestBody EditedPostDto dto) {
		Optional<Post> optional = postRepository.findById(id);
		if (optional.isPresent()) {
			Post post = dto.update(id, postRepository);
			return ResponseEntity.ok(post);
		}
		return ResponseEntity.notFound().build();
	}
	
	@PatchMapping("/{id}/upvotes")
	@Transactional
	public ResponseEntity<?> editPost(@PathVariable Long id) {		
		Optional<Post> optional = postRepository.findById(id);
		if (optional.isPresent()) {
			Post post = optional.get();
			post.setUpvotes();
			return ResponseEntity.ok(optional.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable Long id){
		Optional<Post> optional = postRepository.findById(id);
		if(optional.isPresent()) {
			postRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
}

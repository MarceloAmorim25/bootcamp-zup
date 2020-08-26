package br.com.postgram.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.postgram.models.Post;
import br.com.postgram.repositories.PostRepository;


@RestController
@RequestMapping("/posts")
public class PostController {

	@Autowired
	private PostRepository postRepository;

		
	@GetMapping
	public List<Post> listar() {
		return postRepository.findAll();
	}

	
	@GetMapping("/{postId}")
	public ResponseEntity<Post> buscarPorId(@PathVariable Long postId) {
		
		Optional<Post> post = postRepository.findById(postId);
		
		if(post.isPresent()) {
			return ResponseEntity.ok(post.get());
		}
						
		return ResponseEntity.notFound().build();		
	}
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Post cadastrar(@Valid @RequestBody Post post) {
		
		return postRepository.save(post);
		
	}
	
	
	@PutMapping("/{postId}")
	public ResponseEntity<Post> atualizar(@Valid @PathVariable Long postId,
			@RequestBody Post post) {
		
		if(!postRepository.existsById(postId)) {
			return ResponseEntity.notFound().build();
		}
		
		post.setId(postId);
		postRepository.save(post);
		
		return ResponseEntity.ok(post);
	}
	
	
	@DeleteMapping("/{postId}")
	public ResponseEntity<Void> delete(@PathVariable Long postId){
		
		if(!postRepository.existsById(postId)) {
			return ResponseEntity.notFound().build();
		}
		
		Post post = postRepository
				.findById(postId)
				.orElseThrow();
		
		postRepository.delete(post);
		
		return ResponseEntity.noContent().build();
	}
	
	
}

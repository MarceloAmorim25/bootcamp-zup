package br.com.postgram.controllers;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.postgram.models.Post;
import br.com.postgram.repositories.PostPhotoRepository;
import br.com.postgram.repositories.PostRepository;


@RestController
@RequestMapping("/posts")
public class PostController {

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private PostPhotoRepository postPhotoRepository;
		
	@GetMapping
	public List<Post> getAll() {
		return postRepository.findAll();
	}

	
	@GetMapping("/{postId}")
	public ResponseEntity<Post> getById(@PathVariable Long postId) {
		
		Optional<Post> post = postRepository.findById(postId);
		
		if(post.isPresent()) {
			return ResponseEntity.ok(post.get());
		}
						
		return ResponseEntity.notFound().build();		
	}
	
	
	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public Post create(@Valid @RequestBody Post post, @RequestParam MultipartFile file) {
		
		var fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
		
		var filePhoto = Path.of("/", fileName);
		
		try {
			
			file.transferTo(filePhoto);
			
		} catch (IllegalStateException | IOException e) {
			
			throw new RuntimeException(e);
			
		}
		
		return postRepository.save(post);
		
	}
	
	
	@PutMapping("/{postId}")
	public ResponseEntity<Post> update(@Valid @PathVariable Long postId,
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

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

import br.com.postgram.models.Comment;
import br.com.postgram.repositories.CommentRepository;

@RestController
@RequestMapping("/comments")
public class CommentController {
	
	@Autowired
	private CommentRepository commentRepository;

		
	@GetMapping
	public List<Comment> listar() {
		return commentRepository.findAll();
	}

	
	@GetMapping("/{postId}")
	public ResponseEntity<Comment> buscarPorId(@PathVariable Long postId) {
		
		Optional<Comment> comment = commentRepository.findById(postId);
		
		if(comment.isPresent()) {
			return ResponseEntity.ok(comment.get());
		}
						
		return ResponseEntity.notFound().build();		
	}
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Comment cadastrar(@Valid @RequestBody Comment comment) {
		
		return commentRepository.save(comment);
		
	}
	
	
	@PutMapping("/{postId}")
	public ResponseEntity<Comment> atualizar(@Valid @PathVariable Long commentId,
			@RequestBody Comment comment) {
		
		if(!commentRepository.existsById(commentId)) {
			return ResponseEntity.notFound().build();
		}
		
		comment.setId(commentId);
		commentRepository.save(comment);
		
		return ResponseEntity.ok(comment);
	}
	
	
	@DeleteMapping("/{postId}")
	public ResponseEntity<Void> delete(@PathVariable Long commentId){
		
		if(!commentRepository.existsById(commentId)) {
			return ResponseEntity.notFound().build();
		}
		
		Comment post = commentRepository
				.findById(commentId)
				.orElseThrow();
		
		commentRepository.delete(post);
		
		return ResponseEntity.noContent().build();
	}

}

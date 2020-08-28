package br.com.postgram.controllers;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import br.com.postgram.dtos.CommentDto;
import br.com.postgram.models.Comment;
import br.com.postgram.models.Post;
import br.com.postgram.models.User;
import br.com.postgram.repositories.CommentRepository;
import br.com.postgram.repositories.PostRepository;
import br.com.postgram.repositories.UserRepository;


@RestController
public class CommentController {
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UserRepository userRepository;

	
	@PostMapping("users/{userId}/posts/{postId}/comments")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<CommentDto> create(@Valid @RequestBody Comment comment,
			@PathVariable Long postId, @PathVariable Long userId) {
			
			
		if(userRepository.existsById(userId) && postRepository.existsById(postId)) {
			
			User user = userRepository.findById(userId).orElseThrow();
			Post post = postRepository.findById(postId).orElseThrow();

			comment.setPost(post);
			comment.setUser(user);
			
			commentRepository.save(comment);
			
			CommentDto commentDto = new CommentDto(comment);		
			
			return ResponseEntity.ok(commentDto);
		}
		
		return ResponseEntity.notFound().build();	
	}
	
	
	@PutMapping("users/{userId}/posts/{postId}/comments/{commentId}")
	public ResponseEntity<Comment> update(@Valid @PathVariable Long commentId,
			@RequestBody Comment comment, @PathVariable Long postId, 
			@PathVariable Long userId) {
		
		if(!userRepository.existsById(userId) &&
		   !postRepository.existsById(postId) &&
		   !commentRepository.existsById(commentId)) {
			
			return ResponseEntity.notFound().build();
		}
		
		comment.setId(commentId);
		commentRepository.save(comment);
		
		return ResponseEntity.ok(comment);
	}
	
	
	@DeleteMapping("users/{userId}/posts/{postId}/comments/{commentId}")
	public ResponseEntity<Void> delete(@PathVariable Long commentId, @PathVariable Long postId, 
			@PathVariable Long userId){
		
		if(!userRepository.existsById(userId) &&
		   !postRepository.existsById(postId) &&
		   !commentRepository.existsById(commentId)) {
			
			return ResponseEntity.notFound().build();
		}
		
		Comment post = commentRepository
				.findById(commentId)
				.orElseThrow();
		
		commentRepository.delete(post);
		
		return ResponseEntity.noContent().build();
	}

}

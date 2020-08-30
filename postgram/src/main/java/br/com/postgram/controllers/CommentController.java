package br.com.postgram.controllers;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
import br.com.postgram.models.Friend;
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

	@PostMapping("/users/{userId}/posts/{postId}/comments")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<CommentDto> create(@Valid @RequestBody Comment comment,
			@PathVariable Long postId, @PathVariable Long userId) {
		
		//userId referente ao usuário que comentará
		
		if(friendIsAllowedTo(userId, postId)) {
			
			Optional<Post> post = postRepository.findById(postId);
			Optional<User> user = userRepository.findById(userId);
				
			comment.setPost(post.get());
			comment.setUser(user.get());							
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
		
		if(commentRepository.existsById(commentId) &&
				friendIsAllowedTo(userId, postId)) {
			
			comment.setId(commentId);
			commentRepository.save(comment);
			return ResponseEntity.ok(comment);
				
		}
		
		return ResponseEntity.notFound().build();
	}
	

	@DeleteMapping("users/{userId}/posts/{postId}/comments/{commentId}")
	public ResponseEntity<Void> delete(@PathVariable Long commentId, @PathVariable Long postId, 
			@PathVariable Long userId){
		
		if(commentRepository.existsById(commentId)&&
				friendIsAllowedTo(userId, postId)) {
			

			Optional<Comment> comment = commentRepository.findById(commentId);
			commentRepository.delete(comment.get());
			
		}

		return ResponseEntity.noContent().build();
		
	}
	
	
	public boolean friendIsAllowedTo(Long userId, Long postId) {
		
		//userId referente ao usuário que comentará
	
		User userToComment = userRepository.findById(userId).orElseThrow();
		Post post = postRepository.findById(postId).orElseThrow();
							
		if(!userRepository.existsById(userId) &&
		   !postRepository.existsById(postId)) {
				
			return false;
		}
										
		List<Friend> friends = userToComment.getFriends();
		Long postIdentification = post.getUser().getId();
						
		List<Friend> userFriend = friends.stream()
						.filter(f -> f.getId() == postIdentification)
						.collect(Collectors.toList());
			
		if(!userFriend.isEmpty()) {
			return true;
		}
		
		return false;
	}
}

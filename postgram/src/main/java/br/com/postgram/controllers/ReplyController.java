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
import br.com.postgram.dtos.ReplyDto;
import br.com.postgram.models.Comment;
import br.com.postgram.models.Post;
import br.com.postgram.models.Reply;
import br.com.postgram.models.User;
import br.com.postgram.repositories.CommentRepository;
import br.com.postgram.repositories.PostRepository;
import br.com.postgram.repositories.ReplyRepository;
import br.com.postgram.repositories.UserRepository;


@RestController
public class ReplyController {
	
	@Autowired
	private ReplyRepository replyRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CommentRepository commentRepository;


	@PostMapping("/users/{userId}/posts/{postId}/comments/{commentId}/replies")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<ReplyDto> create(@Valid @RequestBody Reply reply,
			@PathVariable Long postId, @PathVariable Long userId,
			@PathVariable Long commentId) {
		
		
		if(userRepository.existsById(userId) &&
		   postRepository.existsById(postId) &&
		   commentRepository.existsById(commentId)) {
			
			Post post = postRepository.findById(postId).orElseThrow();
			User user = userRepository.findById(userId).orElseThrow();
			Comment comment = commentRepository.findById(commentId).orElseThrow();
			
			reply.setPost(post);
			reply.setUser(user);
			reply.setComment(comment);
			replyRepository.save(reply);
			
			ReplyDto replyDto = new ReplyDto(reply);		
				
			return ResponseEntity.ok(replyDto);
			
		}
		
		return ResponseEntity.noContent().build();
						
	}
	
	
	@PutMapping("/users/{userId}/posts/{postId}/comments/{commentId}/replies/{replyId}")
	public ResponseEntity<Reply> update(@Valid @PathVariable Long replyId,
			@RequestBody Reply reply, @PathVariable Long postId, @PathVariable Long userId,
			@PathVariable Long commentId) {
		
		if(!userRepository.existsById(userId) &&
		   !postRepository.existsById(postId) &&
		   !commentRepository.existsById(commentId) &&
		   !replyRepository.existsById(replyId)) {
			
			return ResponseEntity.notFound().build();
			
		}
		
		reply.setId(replyId);
		replyRepository.save(reply);
		
		return ResponseEntity.ok(reply);
	}
	
	
	@DeleteMapping("/users/{userId}/posts/{postId}/comments/{commentId}/replies/{replyId}")
	public ResponseEntity<Void> delete(@PathVariable Long replyId, 
			@PathVariable Long postId, @PathVariable Long userId,
			@PathVariable Long commentId){
		
		if(!userRepository.existsById(userId) &&
		   !postRepository.existsById(postId) &&
		   !commentRepository.existsById(commentId) &&
		   !replyRepository.existsById(replyId)) {
					
			return ResponseEntity.notFound().build();
					
		}
		
		Reply reply = replyRepository
				.findById(replyId)
				.orElseThrow();
		
		replyRepository.delete(reply);
		
		return ResponseEntity.noContent().build();
	}

}

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
import br.com.postgram.dtos.ReplyDto;
import br.com.postgram.models.Comment;
import br.com.postgram.models.Friend;
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
				
		if(commentRepository.existsById(commentId) &&
				friendIsAllowedTo(userId, postId)) {
			
			Optional<Post> post = postRepository.findById(postId);
			Optional<User> user = userRepository.findById(userId);
			Optional<Comment> comment = commentRepository.findById(commentId);
						
			reply.setPost(post.get());
			reply.setUser(user.get());
			reply.setComment(comment.get());
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
		
		if(commentRepository.existsById(commentId) &&
		   replyRepository.existsById(replyId) &&
		   friendIsAllowedTo(userId, postId)) {
					
			reply.setId(replyId);
			replyRepository.save(reply);
								
			return ResponseEntity.ok(reply);
		
		}
		
		return ResponseEntity.noContent().build();
	}
	
	
	@DeleteMapping("/users/{userId}/posts/{postId}/comments/{commentId}/replies/{replyId}")
	public ResponseEntity<Void> delete(@PathVariable Long replyId, 
			@PathVariable Long postId, @PathVariable Long userId,
			@PathVariable Long commentId){
		
		if(commentRepository.existsById(commentId) &&
		   replyRepository.existsById(replyId) &&	   
		   friendIsAllowedTo(userId, postId)) {
			
			Reply reply = replyRepository.findById(replyId).orElseThrow();
			replyRepository.delete(reply);
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
				
		//verifica se o usuário que comentará na postagem segue o autor do post
		//por enquanto, apenas usuários autorizados (os quais tiveram seu pedido de seguir aceito)
		//poderão comentar na postagem
			
		List<Friend> userFriend = friends.stream()
						.filter(f -> f.getId() == postIdentification)
						.collect(Collectors.toList());
			
		if(!userFriend.isEmpty()) {
			return true;
		}
		
		return false;
}
}

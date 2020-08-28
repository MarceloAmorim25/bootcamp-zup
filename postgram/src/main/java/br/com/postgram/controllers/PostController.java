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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import br.com.postgram.dtos.PostDto;
import br.com.postgram.models.Post;
import br.com.postgram.models.User;
import br.com.postgram.repositories.PostRepository;
import br.com.postgram.repositories.UserRepository;

@RestController
public class PostController {

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UserRepository userRepository;
	
		
	@GetMapping("users/{userId}/posts")
	public ResponseEntity<List<Post>> getAllPostsByUserId(@PathVariable Long userId) {
		
		if(userRepository.existsById(userId)) {
			
			User user = userRepository.findById(userId).orElseThrow();
			
			List<Post> posts = user.getPosts();
			
			return ResponseEntity.ok(posts);
		}
		
		return ResponseEntity.notFound().build();
				
	}

	
	@GetMapping("users/{userId}/posts/{postId}")
	public ResponseEntity<Post> getById(@PathVariable Long postId) {
		
		Optional<Post> post = postRepository.findById(postId);
		
		if(post.isPresent()) {	
			
			return ResponseEntity.ok(post.get());
		}
						
		return ResponseEntity.notFound().build();		
	}
	
	
	@PostMapping("users/{userId}/posts")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<PostDto> create(@Valid @RequestBody Post post, @PathVariable Long userId) {
					
		if(userRepository.existsById(userId) && post != null) {
			
			User user = userRepository.findById(userId).orElseThrow();
			
			post.setUser(user);	
			postRepository.save(post);
			
			PostDto postDto = new PostDto(post);		
				
			return ResponseEntity.ok(postDto);	
		}
		
		
		return ResponseEntity.notFound().build();
			
	}
	
	
	@PutMapping("users/{userId}/posts/{postId}")
	public ResponseEntity<Post> update(@Valid @PathVariable Long postId,
			@RequestBody Post post) {
		
		if(!postRepository.existsById(postId)) {
			return ResponseEntity.notFound().build();
		}
		
		post.setId(postId);
		postRepository.save(post);
		
		return ResponseEntity.ok(post);
	}
	
	
	@DeleteMapping("users/{userId}/posts/{postId}")
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

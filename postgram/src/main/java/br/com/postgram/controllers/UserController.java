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

import br.com.postgram.models.User;
import br.com.postgram.repositories.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;

		
	@GetMapping
	public List<User> getAll() {
		return userRepository.findAll();
	}

	
	@GetMapping("/{userId}")
	public ResponseEntity<User> getById(@PathVariable Long userId) {
		
		Optional<User> user = userRepository.findById(userId);
		
		if(user.isPresent()) {
			return ResponseEntity.ok(user.get());
		}
						
		return ResponseEntity.notFound().build();	
	}
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public User create(@Valid @RequestBody User user) {
		
		return userRepository.save(user);
		
	}
	
	
	@PutMapping("/{userId}")
	public ResponseEntity<User> update(@Valid @PathVariable Long userId,
			@RequestBody User user) {
		
		if(!userRepository.existsById(userId)) {
			return ResponseEntity.notFound().build();
		}
		
		user.setId(userId);
		userRepository.save(user);
		
		return ResponseEntity.ok(user);
	}
	
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<Void> delete(@PathVariable Long userId){
		
		if(!userRepository.existsById(userId)) {
			return ResponseEntity.notFound().build();
		}
		
		User user = userRepository
				.findById(userId)
				.orElseThrow();
		
		userRepository.delete(user);
		
		return ResponseEntity.noContent().build();
	}
	
}

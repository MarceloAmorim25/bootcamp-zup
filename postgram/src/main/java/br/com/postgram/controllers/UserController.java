package br.com.postgram.controllers;

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

import br.com.postgram.dtos.UserDto;
import br.com.postgram.models.User;
import br.com.postgram.repositories.UserRepository;

@RestController
public class UserController {

	@Autowired
	private UserRepository userRepository;


	@GetMapping("/users/{userId}")
	public UserDto getById(@PathVariable Long userId) {
		
		User user = userRepository.findById(userId).orElseThrow();
		
		UserDto userDto = new UserDto(user);
		
	    return userDto;	
					
	}
	
	
	@PostMapping("/users")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<User> create(@Valid @RequestBody User user) {
		
		if(user != null) {
			
			userRepository.save(user);
			return ResponseEntity.ok(user);
		}
			
		return ResponseEntity.noContent().build();
	}
	
	
	@PutMapping("/users/{userId}")
	public ResponseEntity<User> update(@Valid @PathVariable Long userId,
			@RequestBody User user) {
		
		if(!userRepository.existsById(userId)) {
			return ResponseEntity.notFound().build();
		}
		
		user.setId(userId);
		userRepository.save(user);
		
		return ResponseEntity.ok(user);
	}
	
	
	@DeleteMapping("/users/{userId}")
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

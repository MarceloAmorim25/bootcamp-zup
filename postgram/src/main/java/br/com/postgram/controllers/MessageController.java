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

import br.com.postgram.models.Message;
import br.com.postgram.repositories.MessageRepository;

@RestController
@RequestMapping("/messages")
public class MessageController {
	
	@Autowired
	private MessageRepository messageRepository;

		
	@GetMapping
	public List<Message> listar() {
		return messageRepository.findAll();
	}

	
	@GetMapping("/{messageId}")
	public ResponseEntity<Message> buscarPorId(@PathVariable Long messageId) {
		
		Optional<Message> message = messageRepository.findById(messageId);
		
		if(message.isPresent()) {
			return ResponseEntity.ok(message.get());
		}
						
		return ResponseEntity.notFound().build();		
	}
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Message cadastrar(@Valid @RequestBody Message message) {
		
		return messageRepository.save(message);
		
	}
	
	
	@PutMapping("/{messageId}")
	public ResponseEntity<Message> atualizar(@Valid @PathVariable Long messageId,
			@RequestBody Message message) {
		
		if(!messageRepository.existsById(messageId)) {
			return ResponseEntity.notFound().build();
		}
		
		message.setId(messageId);
		messageRepository.save(message);
		
		return ResponseEntity.ok(message);
	}
	
	
	@DeleteMapping("/{messageId}")
	public ResponseEntity<Void> delete(@PathVariable Long messageId){
		
		if(!messageRepository.existsById(messageId)) {
			return ResponseEntity.notFound().build();
		}
		
		Message message = messageRepository
				.findById(messageId)
				.orElseThrow();
		
		messageRepository.delete(message);
		
		return ResponseEntity.noContent().build();
	}

}

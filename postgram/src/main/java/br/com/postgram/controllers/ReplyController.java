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

import br.com.postgram.models.Reply;
import br.com.postgram.repositories.ReplyRepository;


@RestController
@RequestMapping("/replies")
public class ReplyController {
	
	@Autowired
	private ReplyRepository replyRepository;

		
	@GetMapping
	public List<Reply> listar() {
		return replyRepository.findAll();
	}

	
	@GetMapping("/{replyId}")
	public ResponseEntity<Reply> buscarPorId(@PathVariable Long replyId) {
		
		Optional<Reply> reply = replyRepository.findById(replyId);
		
		if(reply.isPresent()) {
			return ResponseEntity.ok(reply.get());
		}
						
		return ResponseEntity.notFound().build();	
	}
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Reply cadastrar(@Valid @RequestBody Reply reply) {
		
		return replyRepository.save(reply);
		
	}
	
	
	@PutMapping("/{replyId}")
	public ResponseEntity<Reply> atualizar(@Valid @PathVariable Long replyId,
			@RequestBody Reply reply) {
		
		if(!replyRepository.existsById(replyId)) {
			return ResponseEntity.notFound().build();
		}
		
		reply.setId(replyId);
		replyRepository.save(reply);
		
		return ResponseEntity.ok(reply);
	}
	
	
	@DeleteMapping("/{replyId}")
	public ResponseEntity<Void> delete(@PathVariable Long replyId){
		
		if(!replyRepository.existsById(replyId)) {
			return ResponseEntity.notFound().build();
		}
		
		Reply reply = replyRepository
				.findById(replyId)
				.orElseThrow();
		
		replyRepository.delete(reply);
		
		return ResponseEntity.noContent().build();
	}

}

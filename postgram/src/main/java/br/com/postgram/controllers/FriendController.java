package br.com.postgram.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.postgram.dtos.FriendDto;
import br.com.postgram.models.Friend;
import br.com.postgram.models.User;
import br.com.postgram.repositories.FriendRepository;
import br.com.postgram.repositories.UserRepository;

@RestController
public class FriendController{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private FriendRepository friendRepository;
	
	@PutMapping("/users/{userId}/follow/{friendId}")
	public ResponseEntity<FriendDto> follow(@PathVariable Long userId, @PathVariable Long friendId) {
		
		if(!userRepository.existsById(userId)) {		
			return ResponseEntity.notFound().build();
		}
		
		User user = userRepository.findById(userId).orElseThrow();
		User friendToAdd = userRepository.findById(friendId).orElseThrow();
				
		Friend friend = new Friend();
	
		friend.setName(friendToAdd.getName());
		friend.setUser(user);
		friend.setEmail(friendToAdd.getEmail());		
		friend.setId(friendId);
		
		friendRepository.save(friend);

		List<Friend> friends = user.getFriends();
		friends.add(friend);
		
				
		user.setFriends(friends);
		user.setId(userId);
		
		userRepository.save(user);
		
		FriendDto friendDto = new FriendDto(friend);
		
		return ResponseEntity.ok(friendDto);
			
	}
	
	@PutMapping("/users/{userId}/unfollow/{friendId}")
	public ResponseEntity<Friend> unfollow(@PathVariable Long userId, @PathVariable Long friendId) {
		
		if(!userRepository.existsById(userId)) {		
			return ResponseEntity.notFound().build();
		}
		
		User user = userRepository.findById(userId).orElseThrow();
		Friend friend = friendRepository.findById(friendId).orElseThrow();
		
		List<Friend> friends = user.getFriends();
		friends.remove(friend);
		
		user.setFriends(friends);
		user.setId(userId);

		userRepository.save(user);
		
		return ResponseEntity.ok(friend);
	}
	
}

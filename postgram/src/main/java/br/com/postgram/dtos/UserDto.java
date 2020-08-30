package br.com.postgram.dtos;

import java.util.ArrayList;
import java.util.List;

import br.com.postgram.models.Friend;
import br.com.postgram.models.Post;
import br.com.postgram.models.User;


public class UserDto {
	
	private Long id;
	private String username;
	private List<PostDto> posts = new ArrayList<>();
	private List<FriendDto> friends = new ArrayList<>();
	
	public UserDto(User user) {
		this.id = user.getId();
		this.username = user.getName();
		this.posts = convertToDtoList(user.getPosts());
		this.friends = convertFriendsToDtoList(user.getFriends());
	}
	

	public List<FriendDto> getFriends() {
		return friends;
	}


	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}
	

	public List<PostDto> getPosts() {
		return posts;
	}
			
	public List<PostDto> convertToDtoList(List<Post> posts){
		
		List<PostDto> listToBeReturned = new ArrayList<>();
		
		posts.forEach(post -> {
			PostDto postDto = new PostDto(post);
			listToBeReturned.add(postDto);
		});
		
		return listToBeReturned;
	}
	
	public List<FriendDto> convertFriendsToDtoList(List<Friend> friends){
		
		List<FriendDto> listToBeReturned = new ArrayList<>();
		
		friends.forEach(friend -> {
			FriendDto friendDto = new FriendDto(friend);
			listToBeReturned.add(friendDto);
		});
		
		return listToBeReturned;
	}
	
}

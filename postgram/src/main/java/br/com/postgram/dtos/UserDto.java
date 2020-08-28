package br.com.postgram.dtos;

import java.util.ArrayList;
import java.util.List;

import br.com.postgram.models.Post;
import br.com.postgram.models.User;


public class UserDto {
	
	private Long id;
	private String username;
	private List<PostDto> posts = new ArrayList<>();
	
	public UserDto(User user) {
		this.id = user.getId();
		this.username = user.getName();
		this.posts = convertToDtoList(user.getPosts());
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
	
}

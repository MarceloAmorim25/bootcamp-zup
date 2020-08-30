package br.com.postgram.dtos;
import br.com.postgram.models.Friend;


public class FriendDto {
	
	private Long id;
	private String username;

	
	public FriendDto(Friend friend) {
		this.id = friend.getId();
		this.username = friend.getName();
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}
	
	

}

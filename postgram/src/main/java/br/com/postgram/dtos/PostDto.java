package br.com.postgram.dtos;
import br.com.postgram.models.Post;



public class PostDto {
	
	private Long id;
	private String description;

	public PostDto(Post post) {
		this.id = post.getId();
		this.description = post.getDescription();
	}

	public Long getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

}

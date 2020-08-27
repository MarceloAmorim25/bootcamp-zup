package br.com.postgram.forms;

import javax.validation.constraints.NotBlank;
import br.com.postgram.models.Post;

public class PostForm {

	@NotBlank
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public PostForm() {};
	
	public PostForm(String description) {
		this.description = description;
	}
	
	public Post convertTypeToPost() {
		
		Post post = new Post();		
		post.setDescription(description);
		
		return post;		
	}
	
}

package br.com.postgram.dtos;
import java.util.ArrayList;
import java.util.List;

import br.com.postgram.models.Comment;
import br.com.postgram.models.Post;



public class PostDto {
	
	private Long id;
	private String description;
	private List<CommentDto> comments = new ArrayList<>();
	

	public List<CommentDto> getComments() {
		return comments;
	}

	public PostDto(Post post) {
		this.id = post.getId();
		this.description = post.getDescription();
		this.comments = convertToDtoList(post.getComments());
	}

	public Long getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}
	
	public List<CommentDto> convertToDtoList(List<Comment> comments){
		
		List<CommentDto> listToBeReturned = new ArrayList<>();
		
		comments.forEach(comment -> {
			CommentDto commentDto = new CommentDto(comment);
			listToBeReturned.add(commentDto);
		});
		
		return listToBeReturned;
	}

}

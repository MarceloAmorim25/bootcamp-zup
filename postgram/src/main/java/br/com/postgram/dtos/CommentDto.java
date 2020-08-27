package br.com.postgram.dtos;

import br.com.postgram.models.Comment;

public class CommentDto {

	private Long id;
	private String content;
	
	public CommentDto(Comment comment) {
		this.id = comment.getId();
		this.content = comment.getContent();
	}

	
	public Long getId() {
		return id;
	}

	public String getContent() {
		return content;
	}
	
}

package br.com.postgram.forms;

import javax.validation.constraints.NotBlank;

import br.com.postgram.models.Comment;
import lombok.Data;

@Data
public class CommentForm {

	@NotBlank
	private String content;
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public CommentForm() {};
	
	public CommentForm(String content) {
		this.content = content;
	}
	
	public Comment convertTypeToComment() {
		
		Comment comment = new Comment();		
		comment.setContent(content);
		
		return comment;		
	}
		
}

package br.com.postgram.dtos;

import java.util.ArrayList;
import java.util.List;

import br.com.postgram.models.Comment;
import br.com.postgram.models.Reply;

public class CommentDto {

	private Long id;
	private String content;
	private List<ReplyDto> replies = new ArrayList<>();
	
	public CommentDto(Comment comment) {
		this.id = comment.getId();
		this.content = comment.getContent();
		this.replies = convertToDtoList(comment.getReplies());
	}

	
	public Long getId() {
		return id;
	}

	public String getContent() {
		return content;
	}
	
	
	
	public List<ReplyDto> getReplies() {
		return replies;
	}


	public List<ReplyDto> convertToDtoList(List<Reply> replies){
		
		List<ReplyDto> listToBeReturned = new ArrayList<>();
		
		replies.forEach(reply -> {
			ReplyDto replyDto = new ReplyDto(reply);
			listToBeReturned.add(replyDto);
		});
		
		return listToBeReturned;
	}
	
}

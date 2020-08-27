package br.com.postgram.forms;

import javax.validation.constraints.NotBlank;
import br.com.postgram.models.Reply;


public class ReplyForm {

	@NotBlank
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public ReplyForm() {};
	
	public ReplyForm(String content) {
		this.content = content;
	}
	
	public Reply convertTypeToReply() {
		
		Reply reply = new Reply();		
		reply.setContent(content);
		
		return reply;		
	}
	
}

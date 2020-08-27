package br.com.postgram.dtos;
import br.com.postgram.models.Reply;



public class ReplyDto {

	private Long id;
	private String content;

	public ReplyDto(Reply reply) {
		this.id = reply.getId();
		this.content = reply.getContent();
	}

	public Long getId() {
		return id;
	}

	public String getContent() {
		return content;
	}

}

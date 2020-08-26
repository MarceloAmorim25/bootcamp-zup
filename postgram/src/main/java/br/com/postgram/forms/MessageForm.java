package br.com.postgram.forms;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class MessageForm {
	
	@NotBlank
	private String content;

}

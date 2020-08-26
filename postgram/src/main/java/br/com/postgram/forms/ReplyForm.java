package br.com.postgram.forms;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class ReplyForm {

	@NotBlank
	private String content;

}

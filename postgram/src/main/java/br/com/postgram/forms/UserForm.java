package br.com.postgram.forms;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserForm {

	@NotNull
	@Size(min=2,max=40)
	private String username;
	
	@NotNull
	@Email
	@Size(min=5)
	private String email;
	
	@NotNull
	@Size(min=12)
	private String password;

}

package br.com.postgram.forms;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;


public class LoginForm {
	
	private String email;
	private String userPassword;
	
	public String getEmail() {
		return email;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setUserPassword(String userPassword) {
			
		this.userPassword = userPassword;
	}
	public UsernamePasswordAuthenticationToken convert() {

		return new UsernamePasswordAuthenticationToken(email, userPassword);
	}
	
}

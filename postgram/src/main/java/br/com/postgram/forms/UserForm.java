package br.com.postgram.forms;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import br.com.postgram.models.User;


public class UserForm {

	@NotBlank
	@Size(min=2,max=40)
	private String username;
	
	@NotBlank
	@Email
	@Size(min=10)
	private String email;
	
	@NotBlank
	@Size(min=12)
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserForm() {};
	
	public UserForm(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
	}
	
	public User convertTypeToReply() {
		
		User user = new User();	
		
		user.setEmail(email);
		user.setUserPassword(password);
		user.setName(username);
		
		return user;		
	}
	
	
}

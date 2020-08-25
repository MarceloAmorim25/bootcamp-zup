package br.com.postgram.models;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
public class User {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
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
	
	@OneToMany(mappedBy = "user")
	private List<Post> posts = new ArrayList<>();
	
	@OneToMany(mappedBy = "user")
	private List<Message> messages = new ArrayList<>();
	
	@NotNull
	private OffsetDateTime created_at;
	
}

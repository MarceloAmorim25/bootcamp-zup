package br.com.postgram.models;
import java.util.List;

import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class User {

	private Long id;
	private String username;
	private String email;
	private String password;
	
}

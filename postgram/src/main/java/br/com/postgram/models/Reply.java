package br.com.postgram.models;

import java.time.OffsetDateTime;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Reply {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(min=5, max=300)
	private String content;
	
	@ManyToOne
	private Comment comment;
	
	@NotNull
	private OffsetDateTime created_at;
	
}

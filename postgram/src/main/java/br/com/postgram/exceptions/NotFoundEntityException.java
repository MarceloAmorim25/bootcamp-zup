package br.com.postgram.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundEntityException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public NotFoundEntityException(String message) {
		
		super(message);
		
	}	
}

package br.com.postgram.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.postgram.models.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	
	@Value("${postgram.jwt.expiration}")
	private String expiration;
	
	@Value("${postgram.jwt.secret}")
	private String secret;

	public String generateToken(Authentication authentication) {
		
		User loggedUser = (User) authentication.getPrincipal();
		Date createdAt = new Date();
		
		Date expirationDate = new Date(createdAt.getTime() + Long.parseLong(expiration));
		
		return Jwts.builder()
				.setIssuer("postgram API")
				.setSubject(loggedUser.getId().toString())
				.setIssuedAt(createdAt)
				.setExpiration(expirationDate)
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();

	}
	
}

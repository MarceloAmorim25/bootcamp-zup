package br.com.postgram.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.postgram.models.User;
import io.jsonwebtoken.Claims;
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

	public boolean isValidToken(String token) {
		try {
			
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
			
		}catch(Exception e){
			
			return false;
		}
		
	}

	public Long getUserId(String token) {

		Claims claims = Jwts.parser()
							.setSigningKey(this.secret)
							.parseClaimsJws(token)
							.getBody();
			
		return Long.parseLong(claims.getSubject());
		
	}
}

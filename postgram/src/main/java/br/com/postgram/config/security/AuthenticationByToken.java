package br.com.postgram.config.security;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.postgram.models.User;
import br.com.postgram.repositories.UserRepository;

public class AuthenticationByToken extends OncePerRequestFilter {
	
	private TokenService tokenService;
	
	private UserRepository userRepository;
		
	public AuthenticationByToken(TokenService tokenService, UserRepository userRepository) {

		this.tokenService = tokenService;
		this.userRepository = userRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token = recoveryToken(request);
		boolean valid = tokenService.isValidToken(token);
		
		if(valid){
			authenticateUser(token); 
		};
							
		filterChain.doFilter(request, response);
		
	}

	private void authenticateUser(String token) {
		
		Long idUser = tokenService.getUserId(token);
		
		User user = userRepository.findById(idUser).get();
		
		UsernamePasswordAuthenticationToken authentication = 
				new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	private String recoveryToken(HttpServletRequest request) {
		
		String token = request.getHeader("Authorization");
		
		if(token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}
		
		return token.substring(7,token.length());
		
	}
}

package br.com.postgram.controllerTests;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import br.com.postgram.controllers.UserController;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserTests {

	
	@Autowired
	private UserController userController;
	
	
	@Test
	public void shouldNotBeNullController() throws Exception {
		
		assertThat(userController).isNotNull();
					
	}

}

package br.com.postgram.modelTests;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import br.com.postgram.models.User;
import br.com.postgram.repositories.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTests {
	
	@Autowired
	private UserRepository userRepository;
	

	@Test
	public void shouldBeOkToInstanciateUserClass() {
		
		//arrange
		User user = new User();	
		user.setName("testName");
		user.setEmail("testEmail@email.com");
		user.setUserPassword("testPassword");
	
		//act
		
		userRepository.save(user);
		
		//assert	
		assertThat(user).isNotNull();
		assertThat(user.getId()).isNotNull();
		
	} 
    
}

package br.com.postgram.modelTests;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.postgram.models.Post;
import br.com.postgram.repositories.PostRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
class PostTests {
	
	@Autowired
	private PostRepository postRepository;

	@Test
	public void shouldBeOkToInstanciatePostClass() {
		
		//arrange
		Post post = new Post();
		
		post.setDescription("lorem ipsum");
		
		//act
		
		postRepository.save(post);
		
		//assert	
		assertThat(post).isNotNull();
		assertThat(post.getId()).isNotNull();
		
	}
	
	@Test
	public void shouldValidateAtributtesValues() {
		
	}
}
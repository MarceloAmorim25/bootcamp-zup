package br.com.postgram.modelTests;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import br.com.postgram.models.Comment;
import br.com.postgram.repositories.CommentRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentTests {

	@Autowired
	private CommentRepository commentRepository;
	
	@Test
	public void shouldBeOkToInstanciateCommentClass() {
		
		//arrange
		Comment comment1 = new Comment();
		
		//act
		comment1.setContent("Testando comentários");
		commentRepository.save(comment1);
		
		//assert	
		assertThat(comment1).isNotNull();
		assertThat(comment1.getId()).isNotNull();	
		
	}	
}

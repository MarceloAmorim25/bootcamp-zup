package br.com.postgram.modelTests;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import br.com.postgram.models.Reply;
import br.com.postgram.repositories.ReplyRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ReplyTests {
	
	@Autowired
	private ReplyRepository replyRepository;

	@Test
	public void shouldBeOkToInstanciateReplyClass() {
		
		//arrange
		Reply reply = new Reply();	
		reply.setContent("lorem ipsum lorem ipsum lorem ipsum");
	
		//act
		
		replyRepository.save(reply);
		
		//assert	
		assertThat(reply).isNotNull();
		assertThat(reply.getId()).isNotNull();
		
	}

}

package chat.example.chat_application;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class ChatApplicationTests {
	
	@MockBean
	private StateUsers stateUsers;

	@Test
	void contextLoads() {

	}
}

package chat.example.chat_application;

import org.springframework.stereotype.Service;

@Service
public class ChatService {

    public String processMessage(String message) throws InterruptedException {
        Thread.sleep(1000);
        return "Message reçu : " + message;
    }
}
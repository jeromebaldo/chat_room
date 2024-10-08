package chat.example.chat_application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    private final ChatService chatService;

    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @MessageMapping("/sendMessage")
    @SendTo("/broadcast/messages")
    public String handleMessage(String message) throws Exception {
        return chatService.processMessage(message);
    }
}
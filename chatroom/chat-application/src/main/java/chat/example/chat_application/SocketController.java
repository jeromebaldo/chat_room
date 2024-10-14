package chat.example.chat_application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class SocketController {

    private final SocketService socketService;

    @Autowired
    public SocketController(SocketService socketService) {
        this.socketService = socketService;
    }

    @MessageMapping("/sendMessage")
    @SendTo("/broadcast/message")
    public String handleMessage(String message) throws Exception {
        return socketService.processMessage(message);
    }

    
}
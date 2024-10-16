package chat.example.chat_application;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import jakarta.servlet.http.HttpSession;

@Controller
public class SocketController {

    private final SocketService socketService;
    private final StateUsers stateUsers;

    @Autowired
    public SocketController(SocketService socketService, StateUsers stateUsers) {
        this.socketService = socketService;
        this.stateUsers = stateUsers;
    }

    @MessageMapping("/sendMessage")
    @SendTo("/broadcast/message")
    public String handleMessage(String message) throws Exception {
        
        return socketService.processMessage(message);
    }

    @MessageMapping("/initlist")
    @SendTo("/userslist")
    public Map<String, Boolean> initList() throws Exception {

        return stateUsers.getStateUsersList();
    }
}
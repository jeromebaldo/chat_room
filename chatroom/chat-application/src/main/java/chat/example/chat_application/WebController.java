package chat.example.chat_application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Map;
import jakarta.servlet.http.HttpSession;

@Controller
public class WebController {

    private final JdbcTemplate jdbcTemplate;
    private final StateUsers stateUsers;
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public WebController(JdbcTemplate jdbcTemplate, StateUsers stateUsers, SimpMessagingTemplate messagingTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.stateUsers = stateUsers;
        this.messagingTemplate = messagingTemplate;
    }

    @GetMapping("/")
    public String index(Model model) {
        return "connexion";
    }

    @PostMapping("/")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model, HttpSession httpSession) {

        String sqlRequest = "SELECT * FROM users WHERE username = ?";
        List<Map<String, Object>> user = jdbcTemplate.queryForList(sqlRequest, username);

        if (user.isEmpty()) {
            model.addAttribute("error", "Invalid username or password.");
            return "connexion";
        }

        stateUsers.onlineUser(username);
        httpSession.setAttribute("user", user);
        messagingTemplate.convertAndSend("/userslist", stateUsers.getStateUsersList());

        return "redirect:/home";
    }
    
    @GetMapping("/home")
    public String home(Model model, HttpSession session) {

        List<Map<String, Object>> user = (List<Map<String, Object>>) session.getAttribute("user");
        model.addAttribute("user", user);
        model.addAttribute("stateUsersList", stateUsers.getStateUsersList());

        return "home";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        
        List<Map<String, Object>> user = (List<Map<String, Object>>) session.getAttribute("user");
        String username = (String) user.get(0).get("username");
        stateUsers.setUserOffline(username);
        session.invalidate();

        messagingTemplate.convertAndSend("/userslist", stateUsers.getStateUsersList());
        
        System.out.println("Utilisateur déconnecté");
        
        return "redirect:/";
    }
}

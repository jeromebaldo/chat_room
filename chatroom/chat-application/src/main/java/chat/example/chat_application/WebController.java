package chat.example.chat_application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Map;

@Controller
class WebController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/")
    public String index(Model model) {
        return "connexion";
    }

    @PostMapping("/")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model) {
    
        String sqlRequest = "SELECT * FROM users WHERE username = ?";
        
        List<Map<String, Object>> user = jdbcTemplate.queryForList(sqlRequest, username);

        if (user.isEmpty()) {
            model.addAttribute("error", "error");
            return "connexion";
        }
        
        model.addAttribute("user", user);
        
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String home(Model model) {
        return "home";
    }

}

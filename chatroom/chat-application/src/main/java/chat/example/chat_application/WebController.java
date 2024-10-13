package chat.example.chat_application;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class WebController {

    @GetMapping("/")
    public String index() {
        return "connexion";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }    
}

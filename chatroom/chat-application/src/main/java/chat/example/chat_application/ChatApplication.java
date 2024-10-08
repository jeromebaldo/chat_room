package chat.example.chat_application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@SpringBootApplication
public class ChatApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatApplication.class, args);
	}

	@Configuration
	@EnableWebSocketMessageBroker
	class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

		@Override
		public void registerStompEndpoints(StompEndpointRegistry registry) {
			registry.addEndpoint("/chat").withSockJS();
		}

		@Override
		public void configureMessageBroker(org.springframework.messaging.simp.config.MessageBrokerRegistry config) {
			config.enableSimpleBroker("/topic");
			config.setApplicationDestinationPrefixes("/app");
		}
	}

	@Controller
	class ChatController {

		@MessageMapping("/sendMessage")
		@SendTo("/topic/messages")
		public String handleMessage(String message) throws Exception {
			Thread.sleep(1000); // Simuler un délai de traitement
			return "Message reçu : " + message;
		}
	}

	@RestController
	class WebController {
	
		@GetMapping("/")
		public String index() {
			return "<html>" +
				"<head>" +
				"<title>Chat Application</title>" +
				"<script src=\"https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.5.1/sockjs.min.js\"></script>" +
				"<script src=\"https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js\"></script>" +
				"<script type=\"text/javascript\">" +
				"var stompClient = null;" +
	
				"function connect() {" +
				"    var socket = new SockJS('/chat');" +
				"    stompClient = Stomp.over(socket);" +
				"    stompClient.connect({}, function (frame) {" +
				"        console.log('Connected: ' + frame);" +
				"        stompClient.subscribe('/topic/messages', function (message) {" +
				"            showMessage(message.body);" +
				"        });" +
				"    });" +
				"}" +
	
				"function sendMessage() {" +
				"    var message = document.getElementById('message').value;" +
				"    stompClient.send(\"/app/sendMessage\", {}, message);" +
				"}" +
	
				"function showMessage(message) {" +
				"    var response = document.getElementById('response');" +
				"    var p = document.createElement('p');" +
				"    p.appendChild(document.createTextNode(message));" +
				"    response.appendChild(p);" +
				"}" +
				"</script>" +
				"</head>" +
				"<body>" +
				"<h1>Simple Chat Application</h1>" +
				"<input type=\"text\" id=\"message\" placeholder=\"Enter your message here...\">" +
				"<button onclick=\"sendMessage()\">Send</button>" +
				"<div id=\"response\"></div>" +
				"<script type=\"text/javascript\">" +
				"connect();" +
				"</script>" +
				"</body>" +
				"</html>";
		}
	}
}

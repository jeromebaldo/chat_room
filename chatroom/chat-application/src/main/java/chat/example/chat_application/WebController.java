package chat.example.chat_application;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
                "        stompClient.subscribe('/broadcast/messages', function (message) {" +
                "            showMessage(message.body);" +
                "        });" +
                "    });" +
                "}" +

                "function sendMessage() {" +
                "    var message = document.getElementById('message').value;" +
                "    stompClient.send(\"/client/sendMessage\", {}, message);" +
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

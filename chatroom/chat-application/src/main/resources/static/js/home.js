var stompClient;

function connect() {
  var socket = new SockJS("/chat");
  stompClient = Stomp.over(socket);

  stompClient.connect({}, function (frame) {
    console.log("Connected: " + frame);

    stompClient.subscribe("/broadcast/message", function (message) {
      showMessage(message.body);
    });

    stompClient.subscribe("/userslist", function (message) {
      updateUsersOnline(JSON.parse(message.body));
    });

    stompClient.send("/client/initlist", {}, {});
  });
}

function sendMessage() {
  var message = document.getElementById("message").value;

  var usernameCell = document.querySelector('#userInfo td:nth-child(2)');
  var username = usernameCell ? usernameCell.textContent : 'Inconnu';
  
  var userMessage = username + ": " + message;
  console.log(userMessage);
  stompClient.send("/client/sendMessage", {}, userMessage);
  document.getElementById("message").value = ""; 
}

function showMessage(message) {
  var response = document.getElementById("chatBox");
  var p = document.createElement("p");
  p.appendChild(document.createTextNode(message));
  response.appendChild(p);
  response.scrollTop = response.scrollHeight;
}

function updateUsersOnline(users) {
  var userTable = document.getElementById("userListBody");
  userTable.innerHTML = "";

  Object.keys(users).forEach(function (username) {
    var tr = document.createElement("tr");
    var tdUsername = document.createElement("td");
    var tdStatus = document.createElement("td");

    tdUsername.textContent = username;
    tdStatus.textContent = users[username] ? "En ligne" : "Hors ligne";

    tr.appendChild(tdUsername);
    tr.appendChild(tdStatus);
    userTable.appendChild(tr);
    console.log(tr);
  });
}

window.onload = connect;
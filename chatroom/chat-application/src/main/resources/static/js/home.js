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
  var usernameCell = document.getElementById("userNameMessage");
  var username = usernameCell ? usernameCell.textContent : 'Inconnu';
  var userMessage = username + ": " + message;
  stompClient.send("/client/sendMessage", {}, userMessage);
  document.getElementById("message").value = ""; 
}

function showMessage(message) {
  var response = document.getElementsByClassName("list-message")[0];
  var messageUser = document.createElement("p");
  messageUser.className = "message";
  messageUser.textContent = message; 

  response.appendChild(messageUser);
  response.scrollTop = response.scrollHeight;
}

function toggleList(listId, titleElement) {
  var list = document.getElementById(listId);
  var arrow = titleElement.querySelector(".arrow");

  if (list.style.display === "none" || list.style.display === "") {
    list.style.display = "block";
    arrow.innerHTML = "&#9660;";
  } else {
    list.style.display = "none";
    arrow.innerHTML = "&#9658;";
  }
}

function updateUsersOnline(users) {
  var onlineCount = 0;
  var offlineCount = 0;

  var divUserOnline = document.getElementById("online-list");
  var divUserOffline = document.getElementById("offline-list");

  divUserOnline.innerHTML = "";
  divUserOffline.innerHTML = "";

  Object.keys(users).forEach(function (username) {
    var userDiv = document.createElement("div");
    var spanName = document.createElement("span");

    spanName.className = "name";
    spanName.textContent = username; 

    if (users[username]) {
      onlineCount++;
      userDiv.className = "user online";
      userDiv.appendChild(spanName);
      divUserOnline.appendChild(userDiv);
    } else {
      offlineCount++;
      userDiv.className = "user offline";
      userDiv.appendChild(spanName);
      divUserOffline.appendChild(userDiv);
    }
  });

  document.getElementById("online-count").textContent = onlineCount;
  document.getElementById("offline-count").textContent = offlineCount;
}
window.onload = connect;
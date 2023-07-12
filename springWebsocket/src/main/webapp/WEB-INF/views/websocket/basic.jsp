<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<script>
		var socket;
		
		function connect(){
			var url = "ws://localhost:8888/ws/basic";
			socket = new WebSocket(url);
			
			socket.onopen = function(){
				console.log("서버와 연결되었습니다.");
			};
			
			socket.onclose = function(){
				console.log("서버와 연결이 종료되었습니다.");
			};
			
			socket.onerror = function(){
				console.log("에러가 발생했습니다.");
			};
			
			socket.onmessage = function(){
				console.log("메세지가 도착했습니다.");
			};
		}
		
		function disconnect(){
			socket.close();
		}
		
		function send(){
			var text = document.querySelector("#chat").value;
			socket.send(text);
			document.querySelector("#chat").value= "";
		}
	</script>
	<h1>웹소켓 베이직</h1>
	<button onclick="connect();">접속</button>
	<button onclick="disconnect();">종료</button>
	<hr>
	<br>
	<input type="text" id="chat">
	<button onclick="send();">전송</button>
</body>
</html>
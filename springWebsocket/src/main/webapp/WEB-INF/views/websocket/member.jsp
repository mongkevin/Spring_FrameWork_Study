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
			
			if(!socket){
				var url = "ws://localhost:8888/ws/member";	
				socket = new WebSocket(url);
			}
			
			socket.onopen = function(){
				console.log("서버와 연결되었습니다.");
			};
			
			socket.onclose = function(){
				console.log("서버와 연결이 종료되었습니다.");
			};
			
			socket.onerror = function(){
				console.log("서버와 연결 과정에서 오류가 발생되었습니다.");
			};
			
			socket.onmessage = function(e){
				console.log("메세지가 도착했습니다.");
				/* console.log(e.data); */
				var div = document.createElement("div");
				var obj = JSON.parse(e.data);
				div.textContent = obj.userId + ": " +obj.message;
				document.querySelector("#chat-area").appendChild(div);
			};
		}
		
		function disconnect(){
			socket.close();//소켓 종료
			socket=""; //소켓 비우기
		}
		
		function send(){
			var message = document.querySelector("#chat").value;
			socket.send(message);
			document.querySelector("#chat").value = "";
		}
	</script>
	<h1>회원 전용 채팅</h1>
	<h2>로그인 여부: ${not empty loginUser }</h2>
	<button onclick="connect();">접속</button>
	<button onclick="disconnect();">종료</button>
	<hr>
	<br>
	<input type="text" id="chat">
	<button onclick="send();">전송</button>
	<div id="chat-area"></div>
</body>
</html>
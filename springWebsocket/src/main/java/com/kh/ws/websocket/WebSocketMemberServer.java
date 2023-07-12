package com.kh.ws.websocket;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.google.gson.Gson;
import com.kh.ws.model.vo.Message;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WebSocketMemberServer extends TextWebSocketHandler{

	/*
	 * 이 서버는 로그인된 사용자만 메세지를 보낼 수 있다.
	 * */
	
	private Set<WebSocketSession> users = new CopyOnWriteArraySet<>();
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		//접속 메소드
		users.add(session);
		log.info("접속 : {}",users.size());
		log.info("attribute : {}",session.getAttributes().get("loginUser"));
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		
		String userId = (String)session.getAttributes().get("loginUser");
		
		if(userId!=null) {
			
			Message m = Message.builder().userId(userId).message(message.getPayload()).build();
			//메세지랑 아이디 더해서 보내기 [아이디]메세지
//			TextMessage newMessage = new TextMessage("["+userId+"] "+ message.getPayload());
			
			String text = new Gson().toJson(m);
			TextMessage newMessage = new TextMessage(text);
			for(WebSocketSession ws : users) {
				ws.sendMessage(newMessage);
			}
		}
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		
		users.remove(session);
		log.info("접속 종료! 현재 접속자 수 : {}",users.size());
	}
}

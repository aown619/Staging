package com.revature.handlers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;


public class InviteWebSocketHandler extends TextWebSocketHandler {
	//mapping is personid -> session
	public static Map<Integer, WebSocketSession> websockets = new HashMap<>();


	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		//System.out.println(session.getUri().getQuery());
		if (getQueryValue(session.getUri().getQuery(),"persid")!=null)
		{
			websockets.put(Integer.parseInt(getQueryValue(session.getUri().getQuery(),"persid")), session);
			//System.out.println("Connection made invite");
			//System.out.println(websockets);
		}
		else
		{
			System.out.println("Invalid ID provied for invite websocket, cancelling");
			session.close(CloseStatus.BAD_DATA);
		}
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		//String strMessage = message.getPayload();
		//System.out.println(strMessage);
		//ObjectMapper mapper = new ObjectMapper();
		//Invite i = mapper.readValue(strMessage, Invite.class);
		//System.out.println(i);
		//incomplete - add functionality here
		//session.sendMessage(message);
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		super.afterConnectionClosed(session, status);
		//remove session from map if present
		if (websockets.containsValue(session))
		{
			for (Map.Entry<Integer, WebSocketSession> entry: websockets.entrySet())
			{
				if(entry.getValue().equals(session))
				{
					websockets.remove(entry.getKey());
				}
			}
		}
	}
	
	private String getQueryValue(String query, String param)
	{
		if(query.contains(param))
		{
			return query.substring(query.indexOf(param) + param.length() + 1);
		}
		return null;
		
		
	}
}

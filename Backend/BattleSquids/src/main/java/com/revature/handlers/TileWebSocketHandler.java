package com.revature.handlers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.Board;
import com.revature.beans.Game;
import com.revature.beans.Tile;
import com.revature.data.DAOFactory;
import com.revature.services.BoardService;
import com.revature.services.BoardServiceImpl;
import com.revature.services.GameService;
import com.revature.services.GameServiceImp;
import com.revature.services.TileService;
import com.revature.services.TileServiceImpl;

public class TileWebSocketHandler extends TextWebSocketHandler {
	private TileService tileServ;
	//maps a personid to a tile websocketsession
	public static Map<Integer, WebSocketSession> websockets = new HashMap<>();
	private BoardService boardServ = new BoardServiceImpl(DAOFactory.getBoardDAO());
	private GameService gameServ = new GameServiceImp(DAOFactory.getGameDAO());
	
	public TileWebSocketHandler() {
		tileServ = new TileServiceImpl();
	}
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		//System.out.println("Connection made");
		if (getQueryValue(session.getUri().getQuery(),"persid")!=null)
		{
			websockets.put(Integer.parseInt(getQueryValue(session.getUri().getQuery(),"persid")), session);
			//System.out.println("Connection made invite");
			//System.out.println(websockets);
		}
		else
		{
			System.out.println("Invalid ID provied for tile websocket, cancelling");
			session.close(CloseStatus.BAD_DATA);
		}
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String strMessage = message.getPayload();
		System.out.println(websockets);
		//System.out.println(strMessage);
		ObjectMapper mapper = new ObjectMapper();
		Tile t = mapper.readValue(strMessage, Tile.class);
		tileServ.updateTile(t);
		//System.out.println(t);
		
		//we need to find the other person in the game, do so by going up the chain from tile
		Board board = boardServ.getBoardById(t.getBoardId());
		Game game = gameServ.getGameById(board.getGameId());
		//if tile belongs to player 1 send message to player 2 or vice versa
		
		//we need to find the websocket matching this person, that way we can find the opponent
		if(websockets.containsValue(session))
		{
			for(Integer key: websockets.keySet())
			{
				if(websockets.get(key).equals(session))
				{
					if(key.equals(game.getPlayer1().getId()))
					{
						websockets.get(game.getPlayer2().getId()).sendMessage(message);
						return;
					}
					else
					{
						websockets.get(game.getPlayer1().getId()).sendMessage(message);
						return;
					}
				}
			}
		}
		
		
		/*
		//we need to find the other person in the game, do so by going up the chain from tile
		Board board = boardServ.getBoardById(t.getBoardId());
		Game game = gameServ.getGameById(board.getGameId());
		//if tile belongs to player 1 send message to player 2 or vice versa
		if(board.getOwner().getId().equals(game.getPlayer1().getId()))
		{
			websockets.get(game.getPlayer1().getId()).sendMessage(message);
		}
		else if(board.getOwner().getId().equals(game.getPlayer2().getId()))
		{
			websockets.get(game.getPlayer2().getId()).sendMessage(message);
		}
		*/
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		super.afterConnectionClosed(session, status);
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

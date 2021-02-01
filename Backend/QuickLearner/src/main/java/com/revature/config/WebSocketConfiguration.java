package com.revature.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.revature.handlers.ClientWebSocketHandler;
import com.revature.handlers.InviteWebSocketHandler;
import com.revature.handlers.TileWebSocketHandler;

@Configuration
@EnableWebSocket
public class WebSocketConfiguration implements WebSocketConfigurer {

	private final static String TILE_ENDPOINT = "/tileaction";
	private final static String INVITE_ENDPOINT = "/inviteaction";
	private final static String CHAT_ENDPOINT = "/chataction";
	private final static String CLIENT_ENDPOINT = "/clientaction";
	
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(getTileWebSocketHandler(), TILE_ENDPOINT).setAllowedOrigins("http://localhost:4200");
		registry.addHandler(getInviteWebSocketHandler(), INVITE_ENDPOINT).setAllowedOrigins("http://localhost:4200");
		registry.addHandler(getClientWebSocketHandler(), CLIENT_ENDPOINT).setAllowedOrigins("http://localhost:4200");
	}

	@Bean
	public WebSocketHandler getTileWebSocketHandler() {
		return new TileWebSocketHandler();
	}
	
	@Bean
	public WebSocketHandler getInviteWebSocketHandler() {
		return new InviteWebSocketHandler();
	}
	
	@Bean
	public WebSocketHandler getClientWebSocketHandler()
	{
		return new ClientWebSocketHandler();
	}

}

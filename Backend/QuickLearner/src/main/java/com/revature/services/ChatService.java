package com.revature.services;

import java.util.Set;

import com.revature.beans.Chat;

public interface ChatService {
	
	public Integer addChat(Chat c);
	public Set<Chat> getAllChat();
	public Chat getChatbyId(Integer id);
	public void updateChat(Chat c);
	public void deleteChat(Chat c);

}

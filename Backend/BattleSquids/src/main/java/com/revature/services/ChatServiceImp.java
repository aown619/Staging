package com.revature.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.beans.Chat;
import com.revature.data.ChatDAO;
import com.revature.data.DAOFactory;

@Service
public class ChatServiceImp implements ChatService {
	private ChatDAO dao;

	@Autowired
	public ChatServiceImp(ChatDAO c) {
		dao=c;
		
	}
	
	@Override
	public Integer addChat(Chat c) {
		return dao.add(c);
	}

	@Override
	public Set<Chat> getAllChat() {
		
		Set<Chat> chats= dao.getAll();
		return chats;
		
	}

	@Override
	public Chat getChatbyId(Integer id) {
	
		Chat chat=dao.getById(id);
		if(chat !=null) {
			return chat;
		}else return null;
		
	}

	@Override
	public void updateChat(Chat c) {
		dao.update(c);
	}

	@Override
	public void deleteChat(Chat c) {
		dao.delete(c);
	}

}

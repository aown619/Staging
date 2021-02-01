package com.revature.data;

import org.springframework.stereotype.Repository;

import com.revature.beans.Chat;

@Repository
public class ChatHibernate extends GenericHibernate<Chat> implements ChatDAO {

	public ChatHibernate() {
		super(Chat.class);
		// TODO Auto-generated constructor stub
	}

}

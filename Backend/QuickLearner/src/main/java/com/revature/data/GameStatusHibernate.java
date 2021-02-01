package com.revature.data;

import org.springframework.stereotype.Repository;

import com.revature.beans.GameStatus;

@Repository
public class GameStatusHibernate extends GenericHibernate<GameStatus> implements GameStatusDAO{

	public GameStatusHibernate() {
		super(GameStatus.class);
		// TODO Auto-generated constructor stub
	}

}

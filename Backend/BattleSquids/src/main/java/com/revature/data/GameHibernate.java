package com.revature.data;

import org.springframework.stereotype.Repository;

import com.revature.beans.Game;

@Repository
public class GameHibernate extends GenericHibernate<Game> implements GameDAO{

	public GameHibernate() {
		super(Game.class);
		// TODO Auto-generated constructor stub
	}

}

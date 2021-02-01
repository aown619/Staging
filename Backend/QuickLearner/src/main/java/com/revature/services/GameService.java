package com.revature.services;

import java.util.Set;

import com.revature.beans.Game;

public interface GameService {

	public Integer addGame(Game g);
	public Set<Game> getAllGames();
	public Game getGameById(Integer id);
	public void updateGame(Game g);
	public void deleteGame(Game g);
}

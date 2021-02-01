package com.revature.services;

import java.util.Set;

import com.revature.beans.GameStatus;

public interface GameStatusService {
	
	public Integer addGameStatus(GameStatus gs);
	public Set<GameStatus> getAllGameStatus();
	public GameStatus getGameStatusById(Integer id);
	public void updateGameStatus(GameStatus gs);
	public void deleteGameStatus(GameStatus gs);

}

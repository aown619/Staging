package com.revature.services;

import java.util.Set;

import com.revature.beans.Board;

public interface BoardService {
	public Integer addBoard(Board b);
	public Set<Board> getAllBoard();
	public Board getBoardById(Integer id);
	public Board getBoard1ByGameId(Integer id);
	public Board getBoard2ByGameId(Integer id);
	public void updateBoard(Board b);
	public void deleteBoard(Board b);
	
}

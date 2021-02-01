package com.revature.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.beans.Game;
import com.revature.data.DAOFactory;
import com.revature.data.GameDAO;


@Service
public class GameServiceImp implements GameService{
		private GameDAO dao;
		private BoardService boardServ = new BoardServiceImpl(DAOFactory.getBoardDAO());

		@Autowired
	public GameServiceImp(GameDAO g) {
		dao=g;
	}
		
	@Override
	public Integer addGame(Game g) {
		
		return dao.add(g);
	}

	@Override
	public Set<Game> getAllGames() {
		
		Set<Game> games= dao.getAll();
		return games;
	}

	@Override
	public Game getGameById(Integer id) {
		Game result = dao.getById(id);
		result.setBoard1(boardServ.getBoard1ByGameId(id));
		result.setBoard2(boardServ.getBoard2ByGameId(id));
		return result;
	}

	@Override
	public void updateGame(Game g) {
		// TODO Auto-generated method stub
		dao.update(g);
	}

	@Override
	public void deleteGame(Game g) {
		// TODO Auto-generated method stub
		dao.delete(g);
	}

}

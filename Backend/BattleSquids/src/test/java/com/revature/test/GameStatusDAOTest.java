package com.revature.test;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.Test;

import com.revature.beans.GameStatus;
import com.revature.beans.TileStatus;
import com.revature.data.DAOFactory;
import com.revature.data.GameStatusDAO;
import com.revature.data.TileStatusDAO;

public class GameStatusDAOTest {



	@Test
	public void testAddGetByIdDelete()
	{
		GameStatusDAO dao=DAOFactory.getGameStatusDAO();
		GameStatus stat=new GameStatus();
		stat.setName("Win");
		stat.setId(dao.add(stat));
		assertTrue(stat.getId() != -1);
		
		dao.delete(stat);
		
		GameStatus stat2=dao.getById(stat.getId());
		assertNull(stat2);
		
	}
	
	@Test
	public void testUpdate()
	{
GameStatusDAO dao = DAOFactory.getGameStatusDAO();
		
		GameStatus stat = new GameStatus();
		
		stat.setName("newstatus");
		
		stat.setId(dao.add(stat));
		
		stat.setName("now a name");
		
		dao.update(stat);
		
		GameStatus stat2 = dao.getById(stat.getId());
		
		assertTrue(stat.equals(stat2));
		
		dao.delete(stat2);
	}
	
	@Test
	public void testGetall()
	{
		GameStatusDAO dao = DAOFactory.getGameStatusDAO();
		
		GameStatus stat = new GameStatus();
		
		stat.setName("not a name");
		
		stat.setId(dao.add(stat));
		
		GameStatus stat2 = new GameStatus();
		stat2.setName("i am name");
		
		stat2.setId(dao.add(stat2));
		
		Set<GameStatus> results = dao.getAll();
		
		assertTrue(results.contains(stat));
		assertTrue(results.contains(stat2));
		
		dao.delete(stat);
		dao.delete(stat2);
		
		
	}
	
	
}

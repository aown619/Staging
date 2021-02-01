package com.revature.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.revature.beans.Board;
import com.revature.beans.Game;
import com.revature.beans.GameStatus;
import com.revature.beans.Person;
import com.revature.data.BoardDAO;
import com.revature.data.DAOFactory;

public class BoardDAOTest {
	private static Board a;
	private static Board b;
	private static Person p1;
	private static Person p2;
	private static Game g;
	private static BoardDAO dao;
	private static GameStatus status;
	
	@BeforeAll
	public static void init()
	{
		p1 = new Person();
		p2 = new Person();
		
		p1.setUsername("man");
		p2.setPassword("notman");
		p1.setId(DAOFactory.getPersonDAO().add(p1));
		p2.setId(DAOFactory.getPersonDAO().add(p2));
		
		g = new Game();
		g.setBoard1(null);
		g.setBoard2(null);
		g.setPlayer1(p1);
		g.setPlayer2(p2);
		g.setActivePlayerId(p1.getId());
		status = new GameStatus();
		status.setName("temp");
		status.setId(DAOFactory.getGameStatusDAO().add(status));
		g.setStatus(DAOFactory.getGameStatusDAO().getById(status.getId()));
		
		dao = DAOFactory.getBoardDAO();
		g.setId(DAOFactory.getGameDAO().add(g));
		
		
	}
	
	@BeforeEach
	public void setup()
	{
		a = new Board();
		a.setOwner(p1);
		a.setGameId(g.getId());
		
		b = new Board();
		b.setOwner(p2);
		b.setGameId(g.getId());
		
		
	}

	@AfterAll
	public static void shutdown()
	{
		DAOFactory.getGameDAO().delete(g);
		DAOFactory.getPersonDAO().delete(p2);
		DAOFactory.getPersonDAO().delete(p1);
		DAOFactory.getGameStatusDAO().delete(status);
	}

	@Test
	public void testAddGetByIdDelete()
	{

		a.setId(dao.add(a));
		
		assertTrue(a.getId() != -1);
		
		Board c = dao.getById(a.getId());
		
		assertEquals(c,a);
		
		dao.delete(a);
		
		assertTrue(dao.getById(c.getId()) == null);
		
	}
	
	@Test
	public void testUpdate()
	{

		a.setId(dao.add(a));
		
		a.setOwner(p2);
		
		
		dao.update(a);
		
		Board c = dao.getById(a.getId());
		
		assertEquals(c.getOwner(), p2);
		
		dao.delete(c);
	}
	
	@Test
	public void testGetAll()
	{

		
		b.setId(dao.add(b));
		
		Set<Board> res = dao.getAll();
		
		assertTrue(res.contains(b));
		dao.delete(b);
	}
}

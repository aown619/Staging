package com.revature.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Timestamp;
import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.revature.beans.MatchHistory;
import com.revature.beans.Person;
import com.revature.data.MatchHistoryDAO;
import com.revature.data.DAOFactory;

public class MatchHistoryDAOTest {
	public static Person p1;
	public static Person p2;
	
	@BeforeAll
	public static void init()
	{
		p1 = new Person();
		p2 = new Person();
		
		p1.setUsername("man");
		p2.setUsername("notman");
		p1.setId(DAOFactory.getPersonDAO().add(p1));
		p2.setId(DAOFactory.getPersonDAO().add(p2));
	}
	
	@AfterAll
	public static void shutdown()
	{
		DAOFactory.getPersonDAO().delete(p1);
		DAOFactory.getPersonDAO().delete(p2);
	}

	@Test
	public void testAddGetByIdDelete()
	{
		MatchHistoryDAO dao = DAOFactory.getMatchHistoryDAO();
		
		MatchHistory b = new MatchHistory();
		b.setBegin(new Timestamp(0));
		b.setEnd(new Timestamp(100000));
		b.setLoser(p1);
		b.setWinner(p2);
		
		b.setId(dao.add(b));
		
		assertTrue(b.getId() != -1);
		
		MatchHistory c = dao.getById(b.getId());
		
		assertEquals(c,b);
		
		dao.delete(b);
		
		assertTrue(dao.getById(c.getId()) == null);
		
	}
	
	@Test
	public void testUpdate()
	{
		MatchHistoryDAO dao = DAOFactory.getMatchHistoryDAO();
		
		MatchHistory b = new MatchHistory();
		b.setBegin(new Timestamp(0));
		b.setEnd(new Timestamp(100000));
		b.setLoser(p1);
		b.setWinner(p2);
		
		b.setId(dao.add(b));
		
		b.setEnd(new Timestamp(9999));
		
		dao.update(b);
		
		MatchHistory c = dao.getById(b.getId());
		
		assertEquals(b,c);
		
		dao.delete(c);
	}
	
	@Test
	public void testGetAll()
	{
		MatchHistoryDAO dao = DAOFactory.getMatchHistoryDAO();
		
		MatchHistory b = new MatchHistory();
		b.setBegin(new Timestamp(0));
		b.setEnd(new Timestamp(100000));
		b.setLoser(p1);
		b.setWinner(p2);
		
		b.setId(dao.add(b));
		
		Set<MatchHistory> res = dao.getAll();
		assertTrue(res.contains(b));
		
		dao.delete(b);
	}
}

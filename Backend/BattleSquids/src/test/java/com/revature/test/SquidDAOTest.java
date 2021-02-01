package com.revature.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.Test;

import com.revature.beans.Squid;
import com.revature.data.DAOFactory;
import com.revature.data.SquidDAO;

public class SquidDAOTest {

	@Test
	public void testAddGetByIdDelete()
	{
		SquidDAO dao = DAOFactory.getSquidDAO();
		
		Squid b = new Squid();
		b.setName("squid1");
		
		b.setId(dao.add(b));
		
		assertTrue(b.getId() != -1);
		
		Squid c = dao.getById(b.getId());
		
		assertEquals(c,b);
		
		dao.delete(b);
		
		assertTrue(dao.getById(c.getId()) == null);
		
	}
	
	@Test
	public void testUpdate()
	{
		SquidDAO dao = DAOFactory.getSquidDAO();
		
		Squid b = new Squid();
		b.setName("squid1");
		
		b.setId(dao.add(b));
		
		b.setName("squid2");
		
		dao.update(b);
		
		Squid c = dao.getById(b.getId());
		assertEquals(c.getName(),"squid2");
		
		dao.delete(c);
	}
	
	@Test
	public void testGetAll()
	{
		SquidDAO dao = DAOFactory.getSquidDAO();
		
		Squid b = new Squid();
		b.setName("squid1");
		
		b.setId(dao.add(b));
		
		Set<Squid> res = dao.getAll();
		
		assertTrue(res.contains(b));
		
		dao.delete(b);
	}
}

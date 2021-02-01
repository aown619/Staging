package com.revature.data;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.Test;

import com.revature.beans.TileStatus;
import com.revature.data.DAOFactory;
import com.revature.data.TileStatusDAO;

public class TileStatusDAOTest {

	@Test
	public void testAddGetByIdDelete()
	{
		TileStatusDAO dao = DAOFactory.getTileStatusDAO();
		
		TileStatus stat = new TileStatus();
		
		stat.setName("not a name");
		
		stat.setId(dao.add(stat));
		
		assertTrue(stat.getId() != -1);
		
		assertNotNull(dao.getById(stat.getId()));
		
		dao.delete(stat);
		
		
		TileStatus stat2 = dao.getById(stat.getId());
		
		assertNull(stat2);
		
	}
	
	@Test
	public void testUpdate()
	{
		TileStatusDAO dao = DAOFactory.getTileStatusDAO();
		
		TileStatus stat = new TileStatus();
		
		stat.setName("not a name");
		
		stat.setId(dao.add(stat));
		
		stat.setName("now a name");
		
		dao.update(stat);
		
		TileStatus stat2 = dao.getById(stat.getId());
		
		assertTrue(stat.equals(stat2));
		
		dao.delete(stat2);
	}
	
	@Test
	public void testGetall()
	{
		TileStatusDAO dao = DAOFactory.getTileStatusDAO();
		
		TileStatus stat = new TileStatus();
		
		stat.setName("not a name");
		
		stat.setId(dao.add(stat));
		
		TileStatus stat2 = new TileStatus();
		stat2.setName("i am name");
		
		stat2.setId(dao.add(stat2));
		
		Set<TileStatus> results = dao.getAll();
		
		assertTrue(results.contains(stat));
		assertTrue(results.contains(stat2));
		
		dao.delete(stat);
		dao.delete(stat2);
	}
}

package com.revature.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;

import com.revature.beans.InviteType;
import com.revature.data.DAOFactory;
import com.revature.data.InviteTypeDAO;

@TestMethodOrder(OrderAnnotation.class)
public class InviteTypeDAOTest {
	private static InviteTypeDAO inviteTypeDao;
	private static InviteType status;
	
	@BeforeAll
	public static void initializeDao() {
		inviteTypeDao = DAOFactory.getInviteTypeDAO();
		status = new InviteType();
		status.setName("play");
	}
	
	@Order(1)
	@Test
	public void testAdd() {
		Integer newId = inviteTypeDao.add(status);
		assertNotNull(newId);
		assertFalse(newId == -1);
		status.setId(newId);
	}
	
	@Order(2)
	@Test
	public void testGetById() {
		assertTrue(status.equals(inviteTypeDao.getById(status.getId())));
	}
	
	@Order(3)
	@Test
	public void testGetAll() {
		assertTrue(inviteTypeDao.getAll().contains(status));
	}
	
	@Order(3)
	@Test
	public void testUpdate() {
		status.setName("spectate");
		inviteTypeDao.update(status);
		InviteType updatedType = inviteTypeDao.getById(status.getId());
		assertTrue(updatedType.getName().equals("spectate"));
	}
	
	@Order(4)
	@Test
	public void testDelete() {
		inviteTypeDao.delete(status);
		assertFalse(inviteTypeDao.getAll().contains(status));
	}
	
//	@AfterAll
//	public static void cleanUp() {
//		
//	}
}

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

import com.revature.beans.InviteStatus;
import com.revature.data.DAOFactory;
import com.revature.data.InviteStatusDAO;

@TestMethodOrder(OrderAnnotation.class)
public class InviteStatusDAOTest {
	private static InviteStatusDAO inviteStatusDao;
	private static InviteStatus status;
	
	@BeforeAll
	public static void initializeDao() {
		inviteStatusDao = DAOFactory.getInviteStatusDAO();
		status = new InviteStatus();
		status.setName("initiated");
	}
	
	@Order(1)
	@Test
	public void testAdd() {
		Integer newId = inviteStatusDao.add(status);
		assertNotNull(newId);
		assertFalse(newId == -1);
		status.setId(newId);
	}
	
	@Order(2)
	@Test
	public void testGetById() {
		assertTrue(status.equals(inviteStatusDao.getById(status.getId())));
	}
	
	@Order(3)
	@Test
	public void testGetAll() {
		assertTrue(inviteStatusDao.getAll().contains(status));
	}
	
	@Order(3)
	@Test
	public void testUpdate() {
		status.setName("ongoing");
		inviteStatusDao.update(status);
		InviteStatus updatedStatus = inviteStatusDao.getById(status.getId());
		assertTrue(updatedStatus.getName().equals("ongoing"));
	}
	
	@Order(4)
	@Test
	public void testDelete() {
		inviteStatusDao.delete(status);
		assertFalse(inviteStatusDao.getAll().contains(status));
	}
	
//	@AfterAll
//	public static void cleanUp() {
//		
//	}
}

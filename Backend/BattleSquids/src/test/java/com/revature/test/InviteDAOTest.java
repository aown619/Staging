package com.revature.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.revature.data.DAOFactory;
import com.revature.data.GameDAO;
import com.revature.data.GameStatusDAO;
import com.revature.data.InviteDAO;
import com.revature.data.InviteStatusDAO;
import com.revature.data.InviteTypeDAO;
import com.revature.data.PersonDAO;
import com.revature.exceptions.SameSenderAndReceiverException;
import com.revature.beans.Game;
import com.revature.beans.GameStatus;
import com.revature.beans.Invite;
import com.revature.beans.InviteStatus;
import com.revature.beans.InviteType;
import com.revature.beans.Person;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;


@TestMethodOrder(OrderAnnotation.class)
public class InviteDAOTest /* extends GenericDAOTest<Invite>*/{
	private static InviteDAO inviteDao;
	private static PersonDAO personDao;
	private static InviteStatusDAO inviteStatusDao;
	private static InviteTypeDAO inviteTypeDao;
	private static GameDAO gameDao;
	private static GameStatusDAO gameStatusDao;
	private static Person sender;
	private static Person receiver;
	private static Game game;
	private static GameStatus gameStatus;
	private static Invite invite;
	private static Invite updatedInvite;
	private static InviteType inviteType;
	private static InviteStatus inviteStatus;
	private static InviteStatus updateStatus;
	private static Integer inviteId;
	
	@BeforeAll
	public static void initializeSubjects() throws Exception {
		inviteDao = DAOFactory.getInviteDAO();
		personDao = DAOFactory.getPersonDAO();
		inviteTypeDao = DAOFactory.getInviteTypeDAO();
		inviteStatusDao = DAOFactory.getInviteStatusDAO();
		gameDao = DAOFactory.getGameDAO();
		gameStatusDao = DAOFactory.getGameStatusDAO();
		
		sender = new Person();
		sender.setUsername("Sender");
		sender.setId(personDao.add(sender));
		
		receiver = new Person();
		receiver.setUsername("Receiver");
		receiver.setId(personDao.add(receiver));
		
		gameStatus = new GameStatus();
		gameStatus.setName("initiated");
		gameStatus.setId(gameStatusDao.add(gameStatus));
		
		game = new Game();
		game.setPlayer1(sender);
		game.setPlayer2(null);
		game.setActivePlayerId(sender.getId());
		game.setStatus(gameStatus);
		game.setId(gameDao.add(game));
		
		//assuming invite types are "play" and "spectate"
		inviteType = new InviteType();
		inviteType.setName("play");
		inviteType.setId(inviteTypeDao.add(inviteType));
		
		//assuming invite statuses are "sent", "received", "accepted", "rejected"
		inviteStatus = new InviteStatus();
		inviteStatus.setName("sent");
		inviteStatus.setId(inviteStatusDao.add(inviteStatus));
		
		updateStatus = new InviteStatus();
		updateStatus.setName("accepted");
		updateStatus.setId(inviteStatusDao.add(updateStatus));
		
		invite = new Invite();
		invite.setSender(sender);
		invite.setReceiver(receiver);
		invite.setGame(game);
		invite.setStatus(inviteStatus);
		invite.setType(inviteType);
		
		inviteId = -1;
		
		updatedInvite = invite;
		updatedInvite.setId(-2);
	}
	
	@AfterAll
	public static void cleanUp() {
//		inviteDao.delete(invite);
		inviteStatusDao.delete(inviteStatus);
		inviteStatusDao.delete(updateStatus);
		inviteTypeDao.delete(inviteType);
		gameDao.delete(game);
		gameStatusDao.delete(gameStatus);
		personDao.delete(receiver);
		personDao.delete(sender);
	}
	
	//@Override
//	void setDao() {
//		this.dao = InviteDAOTest.inviteDao;
//	}
//	
//	//@Override
//	void setSample() {
//		this.invite = InviteDAOTest.invite;
//	}
//	
//	//@Override
//	void setUpdatedSample() {
//		this.updatedSample = InviteDAOTest.invite;
//		this.updatedSample.setId(4);
//	}

	@Order(1)
	@Test
	void testCannotAddInviteWithSameSenderAndReceiver() {
		invite.setReceiver(sender);
		assertThrows(SameSenderAndReceiverException.class, () -> {
			inviteDao.addInvite(invite);
		});
	}

	@Order(2)
	//@Override
	@Test
	void testAdd() {
		invite.setReceiver(receiver);
		//setSample();
		//super.testAdd();
		try {
			invite.setId(inviteDao.addInvite(invite));
		} catch (SameSenderAndReceiverException e) {
			e.printStackTrace();
		}
		assertNotNull(invite.getId());
		assertNotEquals(invite.getId(), -1);
//		setSampleId(newId);
	}
	
	@Order(3)
	//@Override
	@Test
	void testGetById() {
		invite = inviteDao.getById(invite.getId());
		assertNotNull(invite);
	}
	
	@Order(4)
	//@Override
	@Test
	void testGetAll() {
		//super.testGetAll();
		Set<Invite> all = inviteDao.getAll();
		assertTrue(all.contains(invite));
	}
	
	@Order(5)
	//@Override
	@Test
	void testGetAllInvitesReceivedByPersonWithId() {
		//super.testGetAll();
		Set<Invite> allFromReceiver = inviteDao.getAllInvitesReceivedByPersonWithId(receiver.getId());
		assertTrue(allFromReceiver.contains(invite));
	}
	
	@Order(6)
	//@Override
	@Test
	void testGetAllInvitesForGameWithId() {
		//super.testGetAll();
		Set<Invite> all = inviteDao.getAllInvitesForGameWithId(game.getId());
		assertTrue(all.contains(invite));
	}
	
	@Order(7)
	//@Override
	@Test
	void testGetAllInvitesWithTypeId() {
		//super.testGetAll();
		Set<Invite> all = inviteDao.getAllInvitesWithTypeId(inviteType.getId());
		assertTrue(all.contains(invite));
	}
	
	@Order(8)
	//@Override
	@Test
	void testGetAllInvitesWithStatusId() {
		//super.testGetAll();
		Set<Invite> all = inviteDao.getAllInvitesWithStatusId(inviteStatus.getId());
		assertTrue(all.contains(invite));
	}
	
	@Order(9)
	//@Override
	@Test
	void testGetAllInvitesSentByPersonWithId() {
		//super.testGetAll();
		Set<Invite> all = inviteDao.getAllInvitesSentByPersonWithId(sender.getId());
		assertTrue(all.contains(invite));
	}
	
	@Order(10)
//	@Override
	@Test
	void testUpdate() {
//		super.testUpdate();
		invite.setStatus(updateStatus);
		inviteDao.update(invite);
		assertNotEquals(invite.getStatus(), inviteStatus); //where inviteStatus was "sent"
	}
	
	@Order(11)
	//@Override
	@Test
	void testDelete() {
		//super.testDelete();
		inviteDao.delete(invite);
		assertFalse(inviteDao.getAll().contains(updatedInvite));
	}
}

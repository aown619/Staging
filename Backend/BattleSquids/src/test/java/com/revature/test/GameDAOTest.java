package com.revature.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.revature.beans.Chat;
import com.revature.beans.Game;
import com.revature.beans.GameStatus;
import com.revature.beans.Person;
import com.revature.data.DAOFactory;
import com.revature.data.GameDAO;
import com.revature.data.GameStatusDAO;
import com.revature.data.PersonDAO;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

@TestMethodOrder(OrderAnnotation.class)
public class GameDAOTest {
	
	private static GameDAO gamedao;
	private static Game game;
	private static GameStatus gamestatus;
	private static GameStatusDAO gamestatusdao;
	
	private static PersonDAO persondao;
	private static Person person1,person2;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		person1= new Person();
		gamestatus= new GameStatus();
		//game= newGame();
		persondao= DAOFactory.getPersonDAO();
		gamedao=DAOFactory.getGameDAO();
		person2= new Person();
	
		person1.setPassword("pass");
		person1.setUsername("person1");
		person1.setId(persondao.add(person1));
		
		person2.setPassword("pass");
		person2.setUsername("person2");
		person2.setId(persondao.add(person2));
		
		game=new Game();
		gamedao=DAOFactory.getGameDAO();
		gamestatusdao=DAOFactory.getGameStatusDAO();
		persondao=DAOFactory.getPersonDAO();
		

		game.setPlayer1(person1);
		game.setPlayer2(person2);
		gamestatus.setName("Runing");
		gamestatus.setId(gamestatusdao.add(gamestatus));
		game.setActivePlayerId(person1.getId());
		game.setStatus(gamestatus);
	//	game.setId(gamedao.add(game));
		
		
		
	}
	@Order(1)
	@Test
	public void testAdd() {
	//	System.out.println(chat+"   "+game);
		Integer newId = gamedao.add(game);
	
		assertNotEquals(newId, -1);
		game.setId(newId);
		
		
	}
	@Order(2)
	@Test
	public void getbyid() {
	//	System.out.println(chat+"   "+game);
	Game games =gamedao.getById(game.getId());
	
	//System.out.println(games+"  "+game);
	assertTrue(games.getId() != -1);
		
		
	}

	@Order(3)
	@Test
	public void getallgames() {
	//	System.out.println(chat+"   "+game);
		
		Set<Game> games= gamedao.getAll();
		System.out.println(games+" pl \n"+game);
		assertTrue(games.contains(game));
		
		
	}
	

	@Order(4)
	@Test
	public void updategame() {
	//	System.out.println(chat+"   "+game);
		gamestatus.setName("done");
		gamestatus.setId(gamestatusdao.add(gamestatus));
		
		assertEquals(gamestatus.getName(),"done");
		
	}
	
	
	

	@AfterAll
	public static void shutdown()
	{

		gamedao.delete(game);
		persondao.delete(person1);
		persondao.delete(person2);
		gamestatusdao.delete(gamestatus);
		
	}
	

}

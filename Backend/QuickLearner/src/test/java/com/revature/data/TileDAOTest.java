package com.revature.data;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.revature.beans.Board;
import com.revature.beans.Game;
import com.revature.beans.GameStatus;
import com.revature.beans.Person;
import com.revature.beans.Squid;
import com.revature.beans.Tile;
import com.revature.beans.TileStatus;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;


@TestMethodOrder(OrderAnnotation.class)
public class TileDAOTest {
	private static PersonDAO personDao;
	private static Person player;
	private static GameStatusDAO gsDao;
	private static GameStatus gameStatus;
	private static GameDAO gameDao;
	private static Game game;
	private static BoardDAO boardDao;
	private static Board board;
	private static TileStatusDAO tsDao;
	private static TileStatus tileStatus;
	private static SquidDAO squidDao;
	private static Squid squid;
	private static TileDAO tileDao;
	private static Tile sampleTile;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		personDao = DAOFactory.getPersonDAO();
		player = new Person();
		player.setId(-1);
		player.setUsername("postgres");
		player.setPassword("password");
		player.setId(personDao.add(player));
		
		gsDao = DAOFactory.getGameStatusDAO();
		gameStatus = new GameStatus();
		gameStatus.setId(-1);
		gameStatus.setName("sample");
		gameStatus.setId(gsDao.add(gameStatus));
		
		gameDao = DAOFactory.getGameDAO();
		game = new Game();
		game.setId(-1);
		game.setPlayer1(player);
		game.setPlayer2(null);
		game.setActivePlayerId(player.getId());
		game.setStatus(gameStatus);
		game.setId(gameDao.add(game));
		
		boardDao = DAOFactory.getBoardDAO();
		board = new Board();
		board.setId(-1);
		board.setOwner(player);
		board.setGameId(game.getId());
		board.setId(boardDao.add(board));
		
		tsDao = DAOFactory.getTileStatusDAO();
		tileStatus = new TileStatus();
		tileStatus.setId(-1);
		tileStatus.setName("sample");
		tileStatus.setId(tsDao.add(tileStatus));
		
		squidDao = DAOFactory.getSquidDAO();
		squid = new Squid();
		squid.setId(-1);
		squid.setName("sample");
		squid.setSize(5);
		squid.setId(squidDao.add(squid));
		
		tileDao = DAOFactory.getTileDAO();
		sampleTile = new Tile();
		sampleTile.setId(-1);
		sampleTile.setBoardId(board.getId());
		sampleTile.setStatus(tileStatus);
		sampleTile.setCalamari(squid);
		sampleTile.setX(0);
		sampleTile.setY(0);
		
	}
	
	@Order(1)
	@Test
	void testAdd()
	{
		Integer newId = tileDao.add(sampleTile);
		assertNotEquals(newId, -1);
		sampleTile.setId(newId);
	}
	
	@Order(2)
	@Test
	void testGetById() {
		Tile a = tileDao.getById(sampleTile.getId());
		assertEquals(a, sampleTile);
	}
	
	@Order(3)
	@Test
	void testGetByXY() {
		Tile a = tileDao.getByXY(sampleTile.getBoardId(), sampleTile.getX(), sampleTile.getY());
		assertEquals(a, sampleTile);
	}
	
	@Order(4)
	@Test
	void testGetByX() {
		Set<Tile> xTiles = tileDao.getByX(sampleTile.getBoardId(), sampleTile.getX());
		assertTrue(xTiles.contains(sampleTile));
//		assertEquals(xTiles.size(), 10);
	}
	
	@Order(5)
	@Test
	void testGetByY() {
		Set<Tile> yTiles = tileDao.getByY(sampleTile.getBoardId(), sampleTile.getY());
		assertTrue(yTiles.contains(sampleTile));
//		assertEquals(yTiles.size(), 10);
	}
	
	@Order(6)
	@Test
	void testGetByBoardId() {
		Set<Tile> boardTiles = tileDao.getByBoardId(sampleTile.getBoardId());
		assertTrue(boardTiles.contains(sampleTile));
//		assertEquals(boardTiles.size(), 100);
	}
	
	@Order(7)
	@Test
	void testGetByStatus() {
		Set<Tile> statusTiles = tileDao.getByStatus(sampleTile.getBoardId(), sampleTile.getStatus());
		assertTrue(statusTiles.contains(sampleTile));
	}
	
	@Order(8)
	@Test
	void testGetBySquid() {
		Set<Tile> squidTiles = tileDao.getBySquid(sampleTile.getBoardId(), sampleTile.getCalamari());
		assertTrue(squidTiles.contains(sampleTile));
	}
	
	@Order(9)
	@Test
	void testGetAll() {
		Set<Tile> tiles = tileDao.getAll();
		assertTrue(tiles.contains(sampleTile));
	}
	
	@Order(10)
	@Test
	void testUpdate() {
		Tile a = new Tile();
		a.setId(sampleTile.getId());
		a.setBoardId(sampleTile.getBoardId());
		a.setStatus(sampleTile.getStatus());
		a.setCalamari(sampleTile.getCalamari());
		a.setX(sampleTile.getX() + 1);
		a.setY(sampleTile.getY() + 1);
		
		tileDao.update(a);
		assertNotEquals(sampleTile, tileDao.getById(sampleTile.getId()));

	}
	
	@Order(11)
	@Test
	void testDelete() {
		tileDao.delete(sampleTile);
		assertFalse(tileDao.getAll().contains(sampleTile));
	}
	
	@AfterAll
	static void cleanUp() {
		squidDao.delete(squid);
		tsDao.delete(tileStatus);
		boardDao.delete(board);
		gameDao.delete(game);
		gsDao.delete(gameStatus);
		personDao.delete(player);
	}
}

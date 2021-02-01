package com.revature.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.beans.Board;
import com.revature.beans.Game;
import com.revature.beans.GameStatus;
import com.revature.beans.Person;
import com.revature.beans.Squid;
import com.revature.beans.Tile;
import com.revature.beans.TileStatus;
import com.revature.data.TileDAO;

@ExtendWith(MockitoExtension.class)
class TileServiceTest {
	@Mock
	static TileDAO tileDao;

	@InjectMocks
	static TileServiceImpl tileServ;
	
	static Set<Tile> tilesMock = new HashSet<>();
	static Integer tileSeqMock = 1;
	
//	@BeforeAll
//	static void setUpBeforeClass() throws Exception {
//	}
//
//	@AfterAll
//	static void tearDownAfterClass() throws Exception {
//	}
//
//	@BeforeEach
//	void setUp() throws Exception {
//	}
//
//	@AfterEach
//	void tearDown() throws Exception {
//	}

	@Test
	void testAddTile() {
		Person p = new Person();
		p.setId(1);
		p.setUsername("username");
		p.setPassword("password");

		GameStatus gs = new GameStatus();
		gs.setId(1);
		gs.setName("sample");
		
		Game g = new Game();
		g.setId(1);
		g.setPlayer1(p);
		g.setPlayer2(null);
		g.setStatus(gs);
		
		Board b = new Board();
		b.setId(1);
		b.setOwner(p);
		b.setGameId(g.getId());
		
		TileStatus ts = new TileStatus();
		ts.setId(1);
		ts.setName("sample");
		
		Squid s = new Squid();
		s.setId(1);
		s.setSize(5);
		s.setName("sample");
		
		Tile t = new Tile();
		t.setId(-1);
		t.setBoardId(b.getId());
		t.setCalamari(s);
		t.setStatus(ts);
		t.setX(3);
		t.setY(6);
		
		tilesMock.add(t);
		Tile t2 = t;
		t2.setId(tileSeqMock++);
		
		when(tileDao.add(t)).thenReturn(t2.getId());
		assertEquals(t.getId().intValue(), tileServ.addTile(t).intValue());
		
		verify(tileDao).add(t);
		
	}

	@Test
	void testGetTileById() {
		Tile t = new Tile();
		t.setId(20);
		tilesMock.add(t);
		
		when(tileDao.getById(20)).thenReturn(t);
		assertEquals(t, tileServ.getTileById(20));
		verify(tileDao).getById(20);
	}
	
	@Test
	void testGetTileByXY() {
		Tile t = new Tile();
		t.setBoardId(20);
		t.setX(5);
		t.setY(9);
		tilesMock.add(t);
		
		when(tileDao.getByXY(20, 5, 9)).thenReturn(t);
		assertEquals(t, tileServ.getTileByXY(20, 5, 9));
		verify(tileDao).getByXY(20, 5, 9);
	}
	
	@Test
	void testGetTileByX() {
		Tile t = new Tile();
		t.setBoardId(19);
		t.setX(10);
		Set<Tile> xTileMock = new HashSet<>();
		xTileMock.add(t);
		tilesMock.add(t);
		
		when(tileDao.getByX(19, 10)).then(invocation -> {
			Set<Tile> xTiles = new HashSet<>();
			
			for (Tile tile : tilesMock) {
				if (tile.getBoardId() == 19 && tile.getX() == 10) {
					xTiles.add(tile);
				}
			}
			
			return xTiles;
		});
		
		assertEquals(xTileMock, tileServ.getTileByX(19, 10));
		verify(tileDao).getByX(19, 10);
	}
	
	@Test
	void testGetTileByY() {
		Tile t = new Tile();
		t.setBoardId(19);
		t.setY(10);
		Set<Tile> yTileMock = new HashSet<>();
		yTileMock.add(t);
		tilesMock.add(t);
		
		when(tileDao.getByY(19, 10)).then(invocation -> {
			Set<Tile> yTiles = new HashSet<>();
			
			for (Tile tile : tilesMock) {
				if (tile.getBoardId() == 19 && tile.getY() == 10) {
					yTiles.add(tile);
				}
			}
			
			return yTiles;
		});
		
		assertEquals(yTileMock, tileServ.getTileByY(19, 10));
		verify(tileDao).getByY(19, 10);
	}
	
	@Test
	void testGetTileByBoardId() {
		Tile t = new Tile();
		t.setBoardId(17);
		Set<Tile> boardTileMock = new HashSet<>();
		boardTileMock.add(t);
		tilesMock.add(t);
		
		when(tileDao.getByBoardId(17)).then(invocation -> {
			Set<Tile> boardTiles = new HashSet<>();
			
			for (Tile tile : tilesMock) {
				if (tile.getBoardId() == 17) {
					boardTiles.add(tile);
				}
			}
			
			return boardTiles;
		});
		
		assertEquals(boardTileMock, tileServ.getTileByBoardId(17));
		verify(tileDao).getByBoardId(17);
	}
	
	@Test
	void testGetTileByStatus() {
		TileStatus ts = new TileStatus();
		ts.setId(2);
		ts.setName("sample");
		Tile t = new Tile();
		t.setBoardId(15);
		t.setStatus(ts);
		Set<Tile> statusTileMock = new HashSet<>();
		statusTileMock.add(t);
		tilesMock.add(t);
		
		when(tileDao.getByStatus(15, ts)).then(invocation -> {
			Set<Tile> statusTiles = new HashSet<>();
			
			for (Tile tile : tilesMock) {
				if (tile.getBoardId() == 15 && tile.getStatus() == ts) {
					statusTiles.add(tile);
				}
			}
			return statusTiles;
		});
		
		assertEquals(statusTileMock, tileServ.getTileByStatus(15, ts));
		verify(tileDao).getByStatus(15, ts);
		
	}
	
	@Test
	void testGetTileBySquid() {
		Squid s = new Squid();
		s.setId(2);
		s.setName("sample");
		Tile t = new Tile();
		t.setBoardId(12);
		t.setCalamari(s);
		Set<Tile> squidTileMock = new HashSet<>();
		squidTileMock.add(t);
		tilesMock.add(t);
		
		when(tileDao.getBySquid(12, s)).then(invocation -> {
			Set<Tile> squidTiles = new HashSet<>();
			
			for (Tile tile : tilesMock) {
				if (tile.getBoardId() == 12 && tile.getCalamari() == s) {
					squidTiles.add(tile);
				}
			}
			return squidTiles;
		});
		
		assertEquals(squidTileMock, tileServ.getTileBySquid(12, s));
		verify(tileDao).getBySquid(12, s);
	}
	
	@Test
	void testUpdateTile() {
		Tile t = new Tile();
		tileServ.updateTile(t);
		verify(tileDao).update(t);
	}
	
	@Test
	void testDeleteTile() {
		Tile t = new Tile();
		tileServ.deleteTile(t);
		verify(tileDao).delete(t);
	}
}

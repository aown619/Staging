package com.revature.data;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.revature.beans.Squid;
import com.revature.beans.Tile;
import com.revature.beans.TileStatus;

@Repository
public class TileHibernate extends GenericHibernate<Tile> implements TileDAO {

	public TileHibernate() {
		super(Tile.class);
	}

	@Override
	public Tile getByXY(Integer boardId, Integer x, Integer y) {
		Tile t = null;
		
		try (Session s = hu.getSession()) {
			s.beginTransaction();
			String hql = "FROM Tile WHERE board_id = :board_id AND x_pos = :x AND y_pos = :y";
			Query<Tile> q = s.createQuery(hql, Tile.class);
			q.setParameter("board_id", boardId);
			q.setParameter("x", x);
			q.setParameter("y", y);
			if (q.getSingleResult() != null) t = q.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return t;
	}

	@Override
	public Set<Tile> getByX(Integer boardId, Integer x) {
		Set<Tile> xTiles = new HashSet<>();
		
		try (Session s = hu.getSession()) {
			s.beginTransaction();
			String hql = "FROM Tile WHERE board_id = :board_id AND x_pos = :x";
			Query<Tile> q = s.createQuery(hql, Tile.class);
			q.setParameter("board_id", boardId);
			q.setParameter("x", x);
			if (q.getResultList() != null) xTiles = new HashSet<Tile>(q.getResultList());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return xTiles;
	}

	@Override
	public Set<Tile> getByY(Integer boardId, Integer y) {
		Set<Tile> yTiles = new HashSet<>();
		
		try (Session s = hu.getSession()) {
			s.beginTransaction();
			String hql = "FROM Tile WHERE board_id = :board_id AND y_pos = :y";
			Query<Tile> q = s.createQuery(hql, Tile.class);
			q.setParameter("board_id", boardId);
			q.setParameter("y", y);
			if (q.getResultList() != null) yTiles = new HashSet<Tile>(q.getResultList());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return yTiles;
	}
	@Override
	public Set<Tile> getByBoardId(Integer boardId) {
		Set<Tile> boardTiles = new HashSet<>();
		
		try (Session s = hu.getSession()) {
			s.beginTransaction();
			String hql = "FROM Tile WHERE board_id = :board_id";
			Query<Tile> q = s.createQuery(hql, Tile.class);
			q.setParameter("board_id", boardId);
			if (q.getResultList() != null) boardTiles = new HashSet<Tile>(q.getResultList());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return boardTiles;
	}
	
	@Override
	public Set<Tile> getByStatus(Integer boardId, TileStatus status) {
		Set<Tile> statusTiles = new HashSet<>();
		
		try (Session s = hu.getSession()) {
			s.beginTransaction();
			String hql = "FROM Tile WHERE tile_status_id = :tile_status_id";
			Query<Tile> q = s.createQuery(hql, Tile.class);
			q.setParameter("tile_status_id", status.getId());
			if (q.getResultList() != null) statusTiles = new HashSet<Tile>(q.getResultList());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return statusTiles;
	}

	@Override
	public Set<Tile> getBySquid(Integer boardId, Squid squid) {
		Set<Tile> squidTiles = new HashSet<>();
		
		try (Session s = hu.getSession()) {
			s.beginTransaction();
			String hql = "FROM Tile WHERE squid_id = :squid_id";
			Query<Tile> q = s.createQuery(hql, Tile.class);
			q.setParameter("squid_id", squid.getId());
			if (q.getResultList() != null) squidTiles = new HashSet<Tile>(q.getResultList());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return squidTiles;
	}

}

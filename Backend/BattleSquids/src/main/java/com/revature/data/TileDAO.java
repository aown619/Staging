package com.revature.data;

import java.util.Set;

import com.revature.beans.Squid;
import com.revature.beans.Tile;
import com.revature.beans.TileStatus;

public interface TileDAO extends GenericDAO<Tile> {
	public Tile getByXY(Integer boardId, Integer x, Integer y);
	public Set<Tile> getByX(Integer boardId, Integer x);
	public Set<Tile> getByY(Integer boardId, Integer y);
	public Set<Tile> getByBoardId(Integer boardId);
	public Set<Tile> getByStatus(Integer boardId, TileStatus status);
	public Set<Tile> getBySquid(Integer boardId, Squid squid);
}

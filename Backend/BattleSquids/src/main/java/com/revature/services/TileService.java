package com.revature.services;

import java.util.Set;

import com.revature.beans.Squid;
import com.revature.beans.Tile;
import com.revature.beans.TileStatus;

public interface TileService {
	public Integer addTile(Tile t);
	public Tile getTileById(Integer id);
	public Tile getTileByXY(Integer boardId, Integer x, Integer y);
	public Set<Tile> getTileByX(Integer boardId, Integer x);
	public Set<Tile> getTileByY(Integer boardId, Integer y);
	public Set<Tile> getTileByBoardId(Integer boardId);
	public Set<Tile> getTileByStatus(Integer boardId, TileStatus status);
	public Set<Tile> getTileBySquid(Integer boardId, Squid squid);
	public void updateTile(Tile t);
	public void deleteTile(Tile t);
}

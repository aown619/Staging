package com.revature.services;

import java.util.Set;

import com.revature.beans.TileStatus;

public interface TileStatusService {
	public Integer addTileStatus(TileStatus b);
	public Set<TileStatus> getAllTileStatuss();
	public TileStatus getTileStatusById(Integer id);
	public void updateTileStatus(TileStatus b);
	public void deleteTileStatus(TileStatus b);

}

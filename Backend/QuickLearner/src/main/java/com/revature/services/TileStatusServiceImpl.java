package com.revature.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.beans.TileStatus;
import com.revature.data.TileStatusDAO;

@Service
public class TileStatusServiceImpl implements TileStatusService {
	private TileStatusDAO dao;

	@Autowired
	public TileStatusServiceImpl(TileStatusDAO d)
	{
		dao = d;
	}
	
	@Override
	public Integer addTileStatus(TileStatus b) {
		return dao.add(b);
	}

	@Override
	public Set<TileStatus> getAllTileStatuss() {
		return dao.getAll();
	}

	@Override
	public TileStatus getTileStatusById(Integer id) {
		return dao.getById(id);
	}

	@Override
	public void updateTileStatus(TileStatus b) {
		dao.update(b);

	}

	@Override
	public void deleteTileStatus(TileStatus b) {
		dao.delete(b);

	}

}

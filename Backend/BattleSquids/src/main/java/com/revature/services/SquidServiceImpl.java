package com.revature.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.beans.Squid;
import com.revature.data.DAOFactory;
import com.revature.data.SquidDAO;

@Service
public class SquidServiceImpl implements SquidService {
	SquidDAO dao = DAOFactory.getSquidDAO();
	
	
	@Autowired
	public SquidServiceImpl(SquidDAO dao) {
		this.dao = dao;
	}

	@Override
	public Integer addSquid(Squid b) {
		return dao.add(b);
	}

	@Override
	public Set<Squid> getAllSquid() {
		return dao.getAll();
	}

	@Override
	public Squid getSquidById(Integer id) {
		return dao.getById(id);
	}

	@Override
	public void updateSquid(Squid b) {
		dao.update(b);
	}

	@Override
	public void deleteSquid(Squid b) {
		dao.delete(b);
	}

}

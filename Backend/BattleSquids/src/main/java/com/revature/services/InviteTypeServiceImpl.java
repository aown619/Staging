package com.revature.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.beans.InviteType;
import com.revature.data.DAOFactory;
import com.revature.data.InviteTypeDAO;

@Service
public class InviteTypeServiceImpl implements InviteTypeService{
	InviteTypeDAO dao;// = DAOFactory.getInviteTypeDAO();
	
	@Autowired
	public InviteTypeServiceImpl(InviteTypeDAO dao) {
		this.dao = dao;
	}
	
	@Override
	public Integer addInviteType(InviteType i) {
		return dao.add(i);
	}

	@Override
	public Set<InviteType> getAllInviteTypes() {
		return dao.getAll();
	}

	@Override
	public InviteType getInviteTypeById(Integer id) {
		return dao.getById(id);
	}

	@Override
	public void updateInviteType(InviteType i) {
		dao.update(i);
	}

	@Override
	public void deleteInviteType(InviteType i) {
		dao.delete(i);	
	}

}

package com.revature.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.beans.Invite;
import com.revature.data.DAOFactory;
import com.revature.data.InviteDAO;

@Service
public class InviteServiceImpl implements InviteService {

	InviteDAO dao;// = DAOFactory.getInviteDAO();
	
	@Autowired
	public InviteServiceImpl(InviteDAO dao) {
		this.dao = dao;
	}
	
	public InviteServiceImpl()
	{
		this.dao = DAOFactory.getInviteDAO();
	}
	
	@Override
	public Integer addInvite(Invite i) {
		return dao.add(i);
	}

	@Override
	public Set<Invite> getAllInvites() {
		return dao.getAll();
	}

	@Override
	public Invite getInviteById(Integer id) {
		return dao.getById(id);
	}

	@Override
	public void updateInvite(Invite i) {
		dao.update(i);
	}

	@Override
	public void deleteInvite(Invite i) {
		dao.delete(i);	
	}

	@Override
	public Set<Invite> getAllInvitesReceivedByPersonWithId(Integer id) {
		return dao.getAllInvitesReceivedByPersonWithId(id);
	}

	@Override
	public Set<Invite> getAllInvitesSentByPersonWithId(Integer id) {
		return dao.getAllInvitesSentByPersonWithId(id);
	}

	@Override
	public Set<Invite> getAllInvitesForGameWithId(Integer id) {
		return dao.getAllInvitesForGameWithId(id);
	}

	@Override
	public Set<Invite> getAllInvitesWithTypeId(Integer id) {
		return dao.getAllInvitesWithTypeId(id);
	}

	@Override
	public Set<Invite> getAllInvitesWithStatusId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}

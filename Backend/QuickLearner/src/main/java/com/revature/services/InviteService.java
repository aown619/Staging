package com.revature.services;

import java.util.Set;

import com.revature.beans.Invite;

public interface InviteService {
	public Integer addInvite(Invite b);
	public Set<Invite> getAllInvites();
	public Invite getInviteById(Integer id);
	public void updateInvite(Invite b);
	public void deleteInvite(Invite b);
	
	Set<Invite> getAllInvitesReceivedByPersonWithId(Integer id);

	Set<Invite> getAllInvitesSentByPersonWithId(Integer id);

	Set<Invite> getAllInvitesForGameWithId(Integer id);

	Set<Invite> getAllInvitesWithTypeId(Integer id);

	Set<Invite> getAllInvitesWithStatusId(Integer id);
}

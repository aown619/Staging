package com.revature.data;

import java.util.Set;

import com.revature.beans.Invite;
import com.revature.exceptions.SameSenderAndReceiverException;

public interface InviteDAO extends GenericDAO<Invite> {
	Integer addInvite(Invite i) throws SameSenderAndReceiverException;
	
	Set<Invite> getAllInvitesReceivedByPersonWithId(Integer id);

	Set<Invite> getAllInvitesSentByPersonWithId(Integer id);

	Set<Invite> getAllInvitesForGameWithId(Integer id);

	Set<Invite> getAllInvitesWithTypeId(Integer id);

	Set<Invite> getAllInvitesWithStatusId(Integer id);
}

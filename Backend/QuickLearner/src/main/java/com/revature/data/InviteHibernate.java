package com.revature.data;

import java.util.Set;

import org.springframework.stereotype.Repository;

import com.revature.beans.Invite;
import com.revature.exceptions.SameSenderAndReceiverException;

@Repository
public class InviteHibernate extends GenericHibernate<Invite> implements InviteDAO{

	public InviteHibernate() {
		super(Invite.class);
	}
	
	@Override
	public Set<Invite> getAllInvitesSentByPersonWithId(Integer id){
		return this.getSetOfManyToOneRelations("sender", id);
	}
	
	@Override
	public Set<Invite> getAllInvitesReceivedByPersonWithId(Integer id){
		//simplest approach would be to iterate through result of this.getAll() 
		return this.getSetOfManyToOneRelations("receiver", id);
	}
	
	@Override
	public Set<Invite> getAllInvitesForGameWithId(Integer id){
		return this.getSetOfManyToOneRelations("game", id);
	}
	
	@Override
	public Set<Invite> getAllInvitesWithTypeId(Integer id){
		return this.getSetOfManyToOneRelations("type", id);
	}
	
	@Override
	public Set<Invite> getAllInvitesWithStatusId(Integer id){
		return this.getSetOfManyToOneRelations("status", id);
	}

	@Override
	public Integer addInvite(Invite i) throws SameSenderAndReceiverException {
		if(i.getSender().getId() == i.getReceiver().getId()) {
//		if(i.getSenderId() == i.getReceiverId()) {			
			throw new SameSenderAndReceiverException();
		}else{
			return this.add(i);
		}
	}
}

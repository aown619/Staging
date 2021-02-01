package com.revature.services;

import java.util.Set;

import com.revature.beans.InviteType;

public interface InviteTypeService {
	public Integer addInviteType(InviteType b);
	public Set<InviteType> getAllInviteTypes();
	public InviteType getInviteTypeById(Integer id);
	public void updateInviteType(InviteType b);
	public void deleteInviteType(InviteType b);
}

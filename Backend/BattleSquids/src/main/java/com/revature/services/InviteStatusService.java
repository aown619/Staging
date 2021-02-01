package com.revature.services;

import java.util.Set;

import com.revature.beans.InviteStatus;

public interface InviteStatusService {
	public Integer addInviteStatus(InviteStatus b);
	public Set<InviteStatus> getAllInviteStatuses();
	public InviteStatus getInviteStatusById(Integer id);
	public void updateInviteStatus(InviteStatus b);
	public void deleteInviteStatus(InviteStatus b);
}

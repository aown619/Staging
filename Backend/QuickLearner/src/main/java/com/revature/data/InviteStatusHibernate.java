package com.revature.data;

import org.springframework.stereotype.Repository;

import com.revature.beans.InviteStatus;

@Repository
public class InviteStatusHibernate extends GenericHibernate<InviteStatus> implements InviteStatusDAO{

	public InviteStatusHibernate() {
		super(InviteStatus.class);
	}

}

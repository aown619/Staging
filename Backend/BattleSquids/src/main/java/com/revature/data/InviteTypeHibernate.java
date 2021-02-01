package com.revature.data;

import org.springframework.stereotype.Repository;

import com.revature.beans.InviteType;

@Repository
public class InviteTypeHibernate extends GenericHibernate<InviteType> implements InviteTypeDAO {

	public InviteTypeHibernate() {
		super(InviteType.class);
	}
}

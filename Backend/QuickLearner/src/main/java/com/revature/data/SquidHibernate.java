package com.revature.data;

import org.springframework.stereotype.Repository;

import com.revature.beans.Squid;

@Repository
public class SquidHibernate extends GenericHibernate<Squid> implements SquidDAO {

	public SquidHibernate() {
		super(Squid.class);
		// TODO Auto-generated constructor stub
	}


}

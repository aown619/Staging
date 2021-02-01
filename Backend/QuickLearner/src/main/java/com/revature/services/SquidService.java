package com.revature.services;

import java.util.Set;

import com.revature.beans.Squid;

public interface SquidService {

	public Integer addSquid(Squid b);
	public Set<Squid> getAllSquid();
	public Squid getSquidById(Integer id);
	public void updateSquid(Squid b);
	public void deleteSquid(Squid b);
	
}

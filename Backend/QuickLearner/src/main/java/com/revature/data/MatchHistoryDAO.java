package com.revature.data;

import java.util.Set;

import com.revature.beans.MatchHistory;
import com.revature.beans.Person;

public interface MatchHistoryDAO extends GenericDAO<MatchHistory> {
	public Set<MatchHistory> getByPerson(Person p);
}

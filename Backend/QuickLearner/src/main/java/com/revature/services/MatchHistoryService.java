package com.revature.services;

import java.util.Set;

import com.revature.beans.MatchHistory;
import com.revature.beans.Person;

public interface MatchHistoryService {
	public Integer addMatchHistory(MatchHistory b);
	public Set<MatchHistory> getAllMatchHistory();
	public Set<MatchHistory> getMatchHistoryByPerson(Integer id);
	public MatchHistory getMatchHistoryById(Integer id);
	public void updateMatchHistory(MatchHistory b);
	public void deleteMatchHistory(MatchHistory b);
}

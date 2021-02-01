package com.revature.data;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.revature.beans.MatchHistory;
import com.revature.beans.Person;

@Repository
public class MatchHistoryHibernate extends GenericHibernate<MatchHistory> implements MatchHistoryDAO {

	public MatchHistoryHibernate() {
		super(MatchHistory.class);
		
	}

	@Override
	public Set<MatchHistory> getByPerson(Person p) {
		Set<MatchHistory> personMatches = new HashSet<>();
		
		try (Session s = hu.getSession()){
			s.beginTransaction();
			String hql = "FROM MatchHistory WHERE winner_id = :winner_id OR loser_id = :loser_id";
			Query<MatchHistory> q = s.createQuery(hql, MatchHistory.class);
			q.setParameter("winner_id", p.getId());
			q.setParameter("loser_id", p.getId());
			if (q.getResultList() != null) personMatches = new HashSet<MatchHistory>(q.getResultList());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return personMatches;
	}
}

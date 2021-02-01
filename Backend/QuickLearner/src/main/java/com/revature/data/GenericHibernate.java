package com.revature.data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.utils.HibernateUtil;

public abstract class GenericHibernate<T> implements GenericDAO<T>{
	protected Class<T> type;
	protected HibernateUtil hu = HibernateUtil.getHibernateUtil();

	public GenericHibernate(Class<T> type) {
		this.type = type;
	}
	
	//add:
	@Override
	public Integer add(T t) {
//		Integer id = null;
//		try (Session s = hu.getSession()) {
//			s.beginTransaction();
//			id = (Integer) s.save(t);
//			if (id != null) s.getTransaction().commit();
//			else s.getTransaction().rollback();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return id;
		
		Session s = hu.getSession();
		Transaction tx = null;
		Integer id = null;
		try {
			tx = s.beginTransaction();
			id = (Integer) s.save(t);
			return id;
//			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null)
				tx.rollback();
		} finally {
			s.close();
		}
		return null;
	}
	
	@Override
	public T getById(Integer id) {
//		T t = null;
//		try (Session s = hu.getSession()) {
//			t = s.get(this.type, id);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return t;
		
		Session s = hu.getSession();
		
		try {
			T t = s.get(this.type, id);
			return t;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			s.close();
		}
		return null;
	}
	
	@Override
	public Set<T> getAll() {
		Session s = hu.getSession();
		CriteriaBuilder cb = s.getCriteriaBuilder();
		CriteriaQuery<T> criteria = cb.createQuery(this.type);
		Root<T> root = criteria.from(this.type);
		
		criteria.select(root);
		
		List<T> tList = s.createQuery(criteria).getResultList();
		s.close();
		return new HashSet<T>(tList);
	}
	
	public void update(T t) {		
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.update(t);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null)
				tx.rollback();
		} finally {
			s.close();
		}
	}

	public void delete(T t) {
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.delete(t);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null)
				tx.rollback();
		} finally {
			s.close();
		}
	}
	
	public Set<T> getSetOfManyToOneRelations(String foreignAttr, Integer id){
		try(Session s = hu.getSession()){
			CriteriaBuilder cb = s.getCriteriaBuilder();
			CriteriaQuery<T> cq = cb.createQuery(this.type);
			Root<T> root = cq.from(this.type);
			Predicate predicate = cb.equal(root.get(foreignAttr), id);
			cq.select(root).where(predicate);
			
			List<T> resultList = s.createQuery(cq).getResultList();
			return new HashSet<T>(resultList);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
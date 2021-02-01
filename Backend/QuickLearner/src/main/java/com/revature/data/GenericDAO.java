package com.revature.data;

import java.util.Set;

public interface GenericDAO<T> {
	//create:
	Integer add(T t);
	
	//read:
	Set<T> getAll();
	T getById(Integer id);
	
	//update:
	void update(T t);
	
	//delete:
	void delete(T t);
}


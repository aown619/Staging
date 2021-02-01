package com.revature.services;

import java.util.Set;

import com.revature.beans.Person;

public interface PersonService {
	public Integer addPerson(Person p);
	public Person getPersonById(Integer id);
	public Person getPersonByUsername(String username);
	public Set<Person> getAllPerson();
	public void updatePerson(Person p);
	public void deletePerson(Person p);
}

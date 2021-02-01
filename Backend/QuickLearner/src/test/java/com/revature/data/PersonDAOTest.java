package com.revature.data;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.revature.beans.Person;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;

@TestMethodOrder(OrderAnnotation.class)
class PersonDAOTest {
	private static PersonDAO personDao;
	private static Person samplePerson;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		personDao = DAOFactory.getPersonDAO();
		samplePerson = new Person();
		samplePerson.setId(-1);
		samplePerson.setUsername("postgres");
		samplePerson.setPassword("password");
		
	}

	@Order(1)
	@Test
	void testAdd() {
		Integer newId = personDao.add(samplePerson);
		assertNotEquals(newId,-1);
		samplePerson.setId(newId);
	}
	
	@Order(2)
	@Test
	void testGetById() {
		Person a = personDao.getById(samplePerson.getId());
		assertEquals(a, samplePerson);
	}
	
	@Order(3)
	@Test
	void testGetByUsername() {
		Person a = personDao.getByUsername(samplePerson.getUsername());
		assertEquals(a, samplePerson);
	}
	
	@Order(4)
	@Test
	void testGetAll() {
		Set<Person> persons = personDao.getAll();
		assertTrue(persons.contains(samplePerson));
		int beforeAddSize = persons.size();
		
		Person a = new Person();
		a.setId(-1);
		a.setUsername("sample");
		a.setPassword("password");
		Integer newId = personDao.add(a);
		a.setId(newId);
		persons = personDao.getAll();
		assertTrue(persons.contains(samplePerson) && persons.contains(a));
		int afterAddSize = persons.size();
		assertEquals(beforeAddSize, afterAddSize - 1);
		
		personDao.delete(a);
		
	}
	
	@Order(5)
	@Test
	void testUpdate() {
		Person a = new Person();
		a.setId(samplePerson.getId());
		a.setUsername("updated");
		a.setPassword("updated");
		personDao.update(a);
		assertNotEquals(samplePerson, personDao.getById(samplePerson.getId()));
	}
	
	@Order(6)
	@Test
	void testDelete() {
		personDao.delete(samplePerson);
		assertFalse(personDao.getAll().contains(samplePerson));
	}
	
}

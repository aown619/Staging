package com.revature.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.beans.Person;
import com.revature.data.PersonDAO;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {
	@Mock
	static PersonDAO personDao;
	
	@InjectMocks
	static PersonServiceImpl personServ;
	
	static Set<Person> personsMock = new HashSet<>();
	static Integer personSeqMock = 1;
	
//	@BeforeAll
//	static void setUpBeforeClass() throws Exception {
//	}
//
//	@AfterAll
//	static void tearDownAfterClass() throws Exception {
//	}
//
//	@BeforeEach
//	void setUp() throws Exception {
//	}
//
//	@AfterEach
//	void tearDown() throws Exception {
//	}

	@Test
	void testAddPerson() {
		Person p = new Person();
		p.setId(-1);
		p.setUsername("postgres");
		p.setPassword("password");
		
		personsMock.add(p);
		Person p2 = p;
		p2.setId(personSeqMock++);
		
		when(personDao.add(p)).thenReturn(p2.getId());
		assertEquals(p.getId().intValue(), personServ.addPerson(p).intValue());
		verify(personDao).add(p);
		
	}
	
	@Test
	void testGetPersonById() {
		Person p = new Person();
		p.setId(10);
		personsMock.add(p);
		
		when(personDao.getById(10)).thenReturn(p);
		assertEquals(p, personServ.getPersonById(10));
		verify(personDao).getById(10);
	}
	
	@Test
	void testGetPersonByUsername() {
		Person p = new Person();
		p.setUsername("username");
		personsMock.add(p);
		
		when(personDao.getByUsername("username")).thenReturn(p);
		assertEquals(p, personServ.getPersonByUsername("username"));
		verify(personDao).getByUsername("username");
	}
	
	@Test
	void testGetAllPerson() {
		when(personDao.getAll()).thenReturn(personsMock);
		assertEquals(personsMock, personServ.getAllPerson());
		verify(personDao).getAll();
	}
	
	@Test
	void testUpdatePerson() {
		Person p = new Person();
		personServ.updatePerson(p);
		verify(personDao).update(p);
	}
	
	@Test
	void testDeletePerson() {
		Person p = new Person();
		personServ.deletePerson(p);
		verify(personDao).delete(p);
	}

}

package com.revature.controllers;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.beans.Person;
import com.revature.services.PersonService;

@RestController
@CrossOrigin(origins="http://localhost:4200", allowCredentials="true")
@RequestMapping(path="/users")
public class PersonController {
	private PersonService personServ;
	
	@Autowired
	public PersonController(PersonService p) {
		personServ = p;
	}
	
	@PutMapping
	public ResponseEntity<Person> logIn(HttpSession session, @RequestParam("user") String username,
			@RequestParam("pass") String password) {
		Person person = personServ.getPersonByUsername(username);
		if (person != null) {
			if (person.getPassword().equals(password)) {
				session.setAttribute("user", person);
				return ResponseEntity.ok(person);
			}
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Person> registerUser(HttpSession session, @RequestBody Person person) {
		System.out.println("hello");
		person = personServ.getPersonById(personServ.addPerson(person));
		return ResponseEntity.ok(person);
	}
	
	@DeleteMapping
	public ResponseEntity<Void> logOut(HttpSession session) {
		System.out.println("Bye person");
		session.invalidate();
		return ResponseEntity.ok().build();
	}
	
	@PutMapping(path="/{id}")
	public ResponseEntity<Void> updateUser(HttpSession session, @PathVariable("id") Integer id, 
			@RequestBody Person person) {
		if (person != null && person.getId().equals(id)) {
			personServ.updatePerson(person);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}
	
	@GetMapping(path="/id/{id}")
	public ResponseEntity<Person> getUserById( @PathVariable("id") Integer id) {
		System.out.println("helo person");
		Person person = personServ.getPersonById(id);
		if (person != null) {
			return ResponseEntity.ok(person);
		}
		return ResponseEntity.badRequest().build();
	}
	
	@GetMapping(path="/user/{username}")
	public ResponseEntity<Person> getUserByUsername(HttpSession session, @PathVariable("username") String username) {
		Person person = personServ.getPersonByUsername(username);
		if (person != null) {
			return ResponseEntity.ok(person);
		}
		return ResponseEntity.badRequest().build();
	}
	
	@GetMapping
	public ResponseEntity<Set<Person>> getall() {
		System.out.println("helo person");
		Set<Person> person = personServ.getAllPerson();
		if (person != null) {
			return ResponseEntity.ok(person);
		}
		return ResponseEntity.badRequest().build();
	}
	
}

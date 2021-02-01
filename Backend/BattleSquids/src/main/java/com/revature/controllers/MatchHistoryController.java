package com.revature.controllers;

import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.beans.MatchHistory;
import com.revature.services.MatchHistoryService;
@RestController
@CrossOrigin(origins="http://localhost:4200", allowCredentials="true")
@RequestMapping(path="/matchhistory")
public class MatchHistoryController {
private MatchHistoryService serv;
	
	@Autowired
	public MatchHistoryController(MatchHistoryService t)
	{
		serv = t;
	}
	
	@GetMapping(path="/{id}")
	public ResponseEntity<MatchHistory> getMatchHistoryById(HttpSession session, @PathVariable("id") Integer id)
	{
		MatchHistory result = serv.getMatchHistoryById(id);
		if (result == null)
		{
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(result);
	}
	
	@GetMapping(path="/user/{id}")
	public ResponseEntity<Set<MatchHistory>> getMatchHistoryByPerson(HttpSession session, @PathVariable("id") Integer id) {
		Set<MatchHistory> result = serv.getMatchHistoryByPerson(id);
		if (result == null) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(result);
	}
	
	@PostMapping
	public ResponseEntity<Integer> addMatchHistory(HttpSession session, @RequestBody MatchHistory MatchHistory)
	{
		Integer result = serv.addMatchHistory(MatchHistory);
		if (result == null)
		{
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(result);
	}
	
	@PutMapping
	public ResponseEntity<Void> updateMatchHistory(HttpSession session, @RequestBody MatchHistory MatchHistory)
	{
		serv.updateMatchHistory(MatchHistory);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<Void> deleteMatchHistory(HttpSession session, @PathVariable("id") Integer id)
	{
		MatchHistory MatchHistory = serv.getMatchHistoryById(id);
		if (MatchHistory != null)
		{
			serv.deleteMatchHistory(MatchHistory);			
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.badRequest().build();
	}
}

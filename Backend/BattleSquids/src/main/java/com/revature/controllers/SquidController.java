package com.revature.controllers;

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

import com.revature.beans.Squid;
import com.revature.services.SquidService;

@RestController
@CrossOrigin(origins="http://localhost:4200", allowCredentials="true")
@RequestMapping(path="/squid")
public class SquidController {
private SquidService serv;
	
	@Autowired
	public SquidController(SquidService t)
	{
		serv = t;
	}
	
	@GetMapping(path="/{id}")
	public ResponseEntity<Squid> getSquidById(HttpSession session, @PathVariable("id") Integer id)
	{
		Squid result = serv.getSquidById(id);
		if (result == null)
		{
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(result);
	}
	
	@PostMapping
	public ResponseEntity<Integer> addSquid(HttpSession session, @RequestBody Squid Squid)
	{
		Integer result = serv.addSquid(Squid);
		if (result == null)
		{
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(result);
	}
	
	@PutMapping
	public ResponseEntity<Void> updateSquid(HttpSession session, @RequestBody Squid Squid)
	{
		serv.updateSquid(Squid);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<Void> deleteSquid(HttpSession session, @PathVariable("id") Integer id)
	{
		Squid Squid = serv.getSquidById(id);
		if (Squid != null)
		{
			serv.deleteSquid(Squid);			
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.badRequest().build();
	}
}

package com.revature.controllers;

import javax.servlet.http.HttpSession;

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

import com.revature.beans.GameStatus;
import com.revature.beans.Person;
import com.revature.beans.TileStatus;
import com.revature.services.GameStatusService;

@RestController
@CrossOrigin(origins="http://localhost:4200", allowCredentials="true")
@RequestMapping(path="/game/status")
public class GameStatusController {
	
	private GameStatusService serv;

	public GameStatusController(GameStatusService t) {
		serv=t;
	}
	
	@PostMapping
	public ResponseEntity<Void> addGameStatus(HttpSession session, @RequestBody GameStatus gamestatus) {
		serv.addGameStatus(gamestatus);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping(path="/{id}")
	public ResponseEntity<GameStatus> getGameStatusById(HttpSession session, @PathVariable("id") Integer id)
	{
		System.out.println("Reached");
		GameStatus result = serv.getGameStatusById(id);
		if (result == null)
		{
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(result);
	}
	@PutMapping(path="/{id}")
	public ResponseEntity<Void> updateGameStatus(HttpSession session, @PathVariable("id") Integer id, 
			@RequestBody GameStatus gamestatus) {
		GameStatus Gamestat =	serv.getGameStatusById(id);
		if (Gamestat != null && Gamestat.getId().equals(id)) {
			serv.updateGameStatus(gamestatus);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}
	
	 @DeleteMapping(value = "/{id}")
	    public ResponseEntity<Void> deleteGameStatus(@PathVariable Integer id) {

	    GameStatus gs= serv.getGameStatusById(id);
	    if(gs != null) {
	    	serv.deleteGameStatus(gs);
	    	return ResponseEntity.ok().build();
	    }
	    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	 }     
}

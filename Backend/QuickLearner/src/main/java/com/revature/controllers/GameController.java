package com.revature.controllers;

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
import org.springframework.web.bind.annotation.RestController;

import com.revature.beans.Game;
import com.revature.beans.GameStatus;
import com.revature.beans.Person;
import com.revature.beans.TileStatus;
import com.revature.services.GameService;
import com.revature.services.TileStatusService;

@RestController
@CrossOrigin(origins="http://localhost:4200", allowCredentials="true")
@RequestMapping(path="/game")
public class GameController {
	private GameService serv;
	
	@Autowired
	public GameController(GameService t)
	{
		serv = t;
	}
	@PostMapping
	public ResponseEntity<Integer> addGame(HttpSession session, @RequestBody Game game)
	{
		Integer result = serv.addGame(game);
		if (result == null)
		{
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(result);
	}
	
	
	@GetMapping(path="/{id}")
	public ResponseEntity<Game> getGameById(HttpSession session, @PathVariable("id") Integer id)
	{
		System.out.println("Reached");
		Game result = serv.getGameById(id);
		if (result == null)
		{
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(result);
	}
	
	@PutMapping
	public ResponseEntity<Void> updateGame(HttpSession session,	@RequestBody Game game) {
		//Game games = (Game) session.getAttribute("game");
		serv.updateGame(game);
		return ResponseEntity.ok().build();
		//return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}
	 @DeleteMapping(value = "/{id}")
	    public ResponseEntity<Void> deleteGame(@PathVariable Integer id) {

	    Game gs= serv.getGameById(id);
	    if(gs != null) {
	    	serv.deleteGame(gs);
	    	return ResponseEntity.ok().build();
	    }
	    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	 }     

}

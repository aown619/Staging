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

import com.revature.beans.Chat;
import com.revature.beans.GameStatus;
import com.revature.beans.TileStatus;
import com.revature.services.ChatService;

@RestController
@CrossOrigin(origins="http://localhost:4200", allowCredentials="true")
@RequestMapping(path="/chat")
public class ChatController {
	
	private ChatService serv;
	
	@Autowired
	public ChatController(ChatService s) {
		serv=s;
	} 
	
	@GetMapping(path="/{game_id}")
	public ResponseEntity<Chat> getGameChat(HttpSession session, @PathVariable("game_id") Integer id)
	{
		System.out.println("Reached");
		Chat result = serv.getChatbyId(id);
		if (result == null)
		{
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(result);
	}
	
	@PostMapping
	public ResponseEntity<Integer> addChat(HttpSession session, @RequestBody Chat chat)
	{
		Integer result = serv.addChat(chat);
		if (result == null)
		{
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(result);
	}
	
	@PutMapping(path="/{id}")
	public ResponseEntity<Void> updateChat(HttpSession session, @PathVariable("id") Integer id, 
			@RequestBody Chat chat) {
		Chat ch = serv.getChatbyId(id);
		if (ch != null && ch.getId().equals(id)) {
			serv.updateChat(chat);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}
	
	 @DeleteMapping(value = "/{id}")
	    public ResponseEntity<Void> deleteChat(@PathVariable Integer id) {

	    Chat gs= serv.getChatbyId(id);
	    if(gs != null) {
	    	serv.deleteChat(gs);
	    	return ResponseEntity.ok().build();
	    }
	    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	 }     

}

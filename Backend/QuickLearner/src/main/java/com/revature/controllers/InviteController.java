package com.revature.controllers;

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
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.revature.beans.Invite;
import com.revature.handlers.InviteWebSocketHandler;
import com.revature.services.InviteService;

@RestController
@CrossOrigin(origins="http://localhost:4200", allowCredentials="true")
@RequestMapping(path="/invites")
public class InviteController {
	private InviteService inviteService;
	
	@Autowired
	public InviteController(InviteService i){
		inviteService = i;
	}
	
	@GetMapping(path="/{id}")
	public ResponseEntity<Invite> getInviteById(HttpSession session, @PathVariable("id") Integer id){
		Invite invite = inviteService.getInviteById(id);
		if (invite == null) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(invite);
	}
	
	@GetMapping
	public ResponseEntity<Set<Invite>> getAllInvites(HttpSession session, @RequestParam(defaultValue = "-1",name = "game_id") Integer gameId, @RequestParam(defaultValue = "-1", name ="sender_id") Integer senderId, @RequestParam(defaultValue = "-1" ,name = "receiver_id") Integer receiverId)
	{
		if(gameId != -1)
		{
			return getAllInvitesForGameWithId(session, gameId);
		}
		else if (senderId != -1)
		{
			return getAllInvitesFromSenderWithId(session, senderId);
		}
		else if (receiverId != -1)
		{
			return getAllInvitesForReceiverWithId(session, receiverId);
		}
		else
		{
			return ResponseEntity.badRequest().build();
		}
	}
	
	//@GetMapping(path="?game_id={game_id}")
	public ResponseEntity<Set<Invite>> getAllInvitesForGameWithId(HttpSession session, /*@RequestParam("game_id")*/ Integer gameId){
		Set<Invite> invites = inviteService.getAllInvitesForGameWithId(gameId);
		if (invites == null) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(invites);
	}
	
	//@GetMapping(path="?sender_id={sender_id}")
	public ResponseEntity<Set<Invite>> getAllInvitesFromSenderWithId(HttpSession session, /*@RequestParam("sender_id")*/ Integer senderId){
		Set<Invite> invites = inviteService.getAllInvitesSentByPersonWithId(senderId);
		if (invites == null) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(invites);
	}
	
	//@GetMapping(path="?receiver_id={receiver_id}")
	public ResponseEntity<Set<Invite>> getAllInvitesForReceiverWithId(HttpSession session, /*@RequestParam("receiver_id")*/ Integer receiverId){
		Set<Invite> invites = inviteService.getAllInvitesReceivedByPersonWithId(receiverId);
		if (invites == null) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(invites);
	}
	
	@PutMapping(path="/{id}")
	public ResponseEntity<Void> updateInvite(HttpSession session, @PathVariable("id") Integer id, @RequestBody Invite invite){
		Invite inviteToUpdate = inviteService.getInviteById(id);
		if (inviteToUpdate == null) {
			return ResponseEntity.badRequest().build();
		}
		
		inviteService.updateInvite(invite);
		Invite updatedInvite = inviteService.getInviteById(id);
		
		//check if any websockets need to be notified of update
		//System.out.println(InviteWebSocketHandler.websockets);
		if(InviteWebSocketHandler.websockets.containsKey(updatedInvite.getSender().getId()))
		{
			WebSocketSession sesh = InviteWebSocketHandler.websockets.get(updatedInvite.getSender().getId());
			//System.out.println("websocket found, sending message");
			if(updatedInvite.getStatus().getId() == 2)
			{
				TextMessage message = new TextMessage("accepted");
				//System.out.println(message);
				//System.out.println(sesh);
				try {
					sesh.sendMessage(message);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if(updatedInvite.getStatus().getId() == 3)
			{
				TextMessage message = new TextMessage("rejected");
				try {
					sesh.sendMessage(message);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		if (updatedInvite.equals(invite)) return ResponseEntity.ok().build();
		return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
	}
	
	@PostMapping
	public ResponseEntity<Integer> addInvite(HttpSession session, @RequestBody Invite invite){
		Integer newId = inviteService.addInvite(invite);
		if (newId == null) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(newId);
	}
	
	
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<Void> deleteInvite(HttpSession session, @PathVariable("id") Integer id){
		Invite inviteToDelete = inviteService.getInviteById(id);
		if (inviteToDelete == null) {
			return ResponseEntity.badRequest().build();
		}
		inviteService.deleteInvite(inviteToDelete);
		if (inviteService.getInviteById(id) != null) return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
		return ResponseEntity.ok().build();
	}
}

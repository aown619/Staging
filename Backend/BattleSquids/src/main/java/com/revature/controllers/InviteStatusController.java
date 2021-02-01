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

import com.revature.beans.InviteStatus;
import com.revature.services.InviteStatusService;

@RestController
@CrossOrigin(origins="http://localhost:4200", allowCredentials="true")
@RequestMapping(path="/inviteStatuses")
public class InviteStatusController {
	private InviteStatusService service;
	
	@Autowired
	public InviteStatusController(InviteStatusService i){
		service = i;
	}
	
	@GetMapping(path="/{id}")
	public ResponseEntity<InviteStatus> getInviteStatusById(HttpSession session, @PathVariable("id") Integer id){
		InviteStatus invite = service.getInviteStatusById(id);
		if (invite == null) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(invite);
	}
	
	@PutMapping(path="/{id}")
	public ResponseEntity<Void> updateInviteStatus(HttpSession session, @PathVariable("id") Integer id, @RequestBody InviteStatus inviteStatus){
		InviteStatus statusToUpdate = service.getInviteStatusById(id);
		if (statusToUpdate == null) {
			return ResponseEntity.badRequest().build();
		}
		
		service.updateInviteStatus(inviteStatus);
		InviteStatus updatedStatus = service.getInviteStatusById(id);
		if (!updatedStatus.equals(statusToUpdate)) return ResponseEntity.ok().build();
		return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
	}
	
	@PostMapping
	public ResponseEntity<Integer> addInviteStatus(HttpSession session, @RequestBody InviteStatus inviteStatus){
		Integer newId = service.addInviteStatus(inviteStatus);
		if (newId == null) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(newId);
	}
	
	@DeleteMapping
	public ResponseEntity<Void> deleteInviteStatus(HttpSession session, @PathVariable("id") Integer id){
		InviteStatus statusToDelete = service.getInviteStatusById(id);
		if (statusToDelete == null) {
			return ResponseEntity.badRequest().build();
		}
		service.deleteInviteStatus(statusToDelete);
		if (service.getInviteStatusById(id) != null) return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
		return ResponseEntity.ok().build();
	}
}

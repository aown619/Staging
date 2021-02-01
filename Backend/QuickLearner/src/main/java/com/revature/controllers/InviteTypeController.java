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

import com.revature.beans.InviteType;
import com.revature.services.InviteTypeService;

@RestController
@CrossOrigin(origins="http://localhost:4200", allowCredentials="true")
@RequestMapping(path="/inviteTypes")
public class InviteTypeController {
	private InviteTypeService service;
	
	@Autowired
	public InviteTypeController(InviteTypeService i){
		service = i;
	}
	
	@GetMapping(path="/{id}")
	public ResponseEntity<InviteType> getInviteTypeById(HttpSession session, @PathVariable("id") Integer id){
		InviteType invite = service.getInviteTypeById(id);
		if (invite == null) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(invite);
	}
	
	@PutMapping(path="/{id}")
	public ResponseEntity<Void> updateInviteType(HttpSession session, @PathVariable("id") Integer id, @RequestBody InviteType inviteType){
		InviteType typeToUpdate = service.getInviteTypeById(id);
		if (typeToUpdate == null) {
			return ResponseEntity.badRequest().build();
		}
		
		service.updateInviteType(inviteType);
		InviteType updatedType = service.getInviteTypeById(id);
		if (!updatedType.equals(typeToUpdate)) return ResponseEntity.ok().build();
		return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
	}
	
//	@PostMapping
//	public ResponseEntity<Integer> addInviteType(HttpSession session, @RequestBody InviteType inviteType){
//		Integer newId = service.addInviteType(inviteType);
//		if (newId == null) {
//			return ResponseEntity.badRequest().build();
//		}
//		return ResponseEntity.ok(newId);
//	}
	
	@DeleteMapping
	public ResponseEntity<Void> deleteInviteType(HttpSession session, @PathVariable("id") Integer id){
		InviteType typeToDelete = service.getInviteTypeById(id);
		if (typeToDelete == null) {
			return ResponseEntity.badRequest().build();
		}
		service.deleteInviteType(typeToDelete);
		if (service.getInviteTypeById(id) != null) return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
		return ResponseEntity.ok().build();
	}
}
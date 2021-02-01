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

import com.revature.beans.TileStatus;
import com.revature.services.TileStatusService;

@RestController
@CrossOrigin(origins="http://localhost:4200", allowCredentials="true")
@RequestMapping(path="/tile/status")
public class TileStatusController {
	private TileStatusService serv;
	
	@Autowired
	public TileStatusController(TileStatusService t)
	{
		serv = t;
	}
	
	@GetMapping(path="/{id}")
	public ResponseEntity<TileStatus> getTileStatusById(HttpSession session, @PathVariable("id") Integer id)
	{
		TileStatus result = serv.getTileStatusById(id);
		if (result == null)
		{
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(result);
	}
	
	@PostMapping
	public ResponseEntity<Integer> addTileStatus(HttpSession session, @RequestBody TileStatus tilestatus)
	{
		Integer result = serv.addTileStatus(tilestatus);
		if (result == null)
		{
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(result);
	}
	
	@PutMapping
	public ResponseEntity<Void> updateTileStatus(HttpSession session, @RequestBody TileStatus tilestatus)
	{
		serv.updateTileStatus(tilestatus);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<Void> deleteTileStatus(HttpSession session, @PathVariable("id") Integer id)
	{
		TileStatus tilestatus = serv.getTileStatusById(id);
		if (tilestatus != null)
		{
			serv.deleteTileStatus(tilestatus);			
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.badRequest().build();
	}
}

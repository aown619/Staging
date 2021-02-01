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

import com.revature.beans.Board;
import com.revature.services.BoardService;

@RestController
@CrossOrigin(origins="http://localhost:4200", allowCredentials="true")
@RequestMapping(path="/board")
public class BoardController {
private BoardService serv;
	
	@Autowired
	public BoardController(BoardService t)
	{
		serv = t;
	}
	
	@GetMapping(path="/{id}")
	public ResponseEntity<Board> getBoardById(HttpSession session, @PathVariable("id") Integer id)
	{
		Board result = serv.getBoardById(id);
		if (result == null)
		{
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(result);
	}
	
	@PostMapping
	public ResponseEntity<Integer> addBoard(HttpSession session, @RequestBody Board Board)
	{
		Integer result = serv.addBoard(Board);
		if (result == null)
		{
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(result);
	}
	
	@PutMapping
	public ResponseEntity<Void> updateBoard(HttpSession session, @RequestBody Board Board)
	{
		serv.updateBoard(Board);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<Void> deleteBoard(HttpSession session, @PathVariable("id") Integer id)
	{
		Board Board = serv.getBoardById(id);
		if (Board != null)
		{
			serv.deleteBoard(Board);			
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.badRequest().build();
	}
}

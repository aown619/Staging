package com.revature.data;

import org.springframework.stereotype.Repository;

import com.revature.beans.Board;

@Repository
public class BoardHibernate extends GenericHibernate<Board> implements BoardDAO {

	public BoardHibernate() {
		super(Board.class);
		// TODO Auto-generated constructor stub
	}

}

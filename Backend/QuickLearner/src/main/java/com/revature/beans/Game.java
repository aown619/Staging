package com.revature.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table
public class Game {
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="player_1_id")
	private Person player1;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="player_2_id")
	private Person player2;
	

	@Column(name="active_player_id")
	private Integer activePlayerId;


	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="game_status_id")
	private GameStatus status;
	@Transient	
	private Board board1;
	@Transient
	private Board board2;
	
	public Game()
	{
		id = -1;
		player1 = new Person();
		player2 = new Person();
		activePlayerId = -1;
		status = new GameStatus();
		board1 = new Board();
		board2 = new Board();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Person getPlayer1() {
		return player1;
	}

	public void setPlayer1(Person player1) {
		this.player1 = player1;
	}

	public Person getPlayer2() {
		return player2;
	}

	public void setPlayer2(Person player2) {
		this.player2 = player2;
	}

	public Integer getActivePlayerId() {
		return activePlayerId;
	}

	public void setActivePlayerId(Integer activePlayerId) {
		this.activePlayerId = activePlayerId;
	}

	public GameStatus getStatus() {
		return status;
	}

	public void setStatus(GameStatus status) {
		this.status = status;
	}

	public Board getBoard1() {
		return board1;
	}

	public void setBoard1(Board board1) {
		this.board1 = board1;
	}

	public Board getBoard2() {
		return board2;
	}

	public void setBoard2(Board board2) {
		this.board2 = board2;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((activePlayerId == null) ? 0 : activePlayerId.hashCode());
		result = prime * result + ((board1 == null) ? 0 : board1.hashCode());
		result = prime * result + ((board2 == null) ? 0 : board2.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((player1 == null) ? 0 : player1.hashCode());
		result = prime * result + ((player2 == null) ? 0 : player2.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Game other = (Game) obj;
		if (activePlayerId == null) {
			if (other.activePlayerId != null)
				return false;
		} else if (!activePlayerId.equals(other.activePlayerId))
			return false;
		if (board1 == null) {
			if (other.board1 != null)
				return false;
		} else if (!board1.equals(other.board1))
			return false;
		if (board2 == null) {
			if (other.board2 != null)
				return false;
		} else if (!board2.equals(other.board2))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (player1 == null) {
			if (other.player1 != null)
				return false;
		} else if (!player1.equals(other.player1))
			return false;
		if (player2 == null) {
			if (other.player2 != null)
				return false;
		} else if (!player2.equals(other.player2))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Game [id=" + id + ", player1=" + player1 + ", player2=" + player2 + ", activePlayerId=" + activePlayerId
				+ ", status=" + status + ", board1=" + board1 + ", board2=" + board2 + "]";
	}
	
	
}

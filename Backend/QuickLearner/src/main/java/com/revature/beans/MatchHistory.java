package com.revature.beans;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "match_history")
public class MatchHistory {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "time_start")
	private Timestamp begin;
	@Column(name = "time_end")
	private Timestamp end;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="winner_id")
	private Person winner;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="loser_id")
	private Person loser;
	
	public MatchHistory()
	{
		id = -1;
		begin = null;
		end = null;
		winner = new Person();
		loser = new Person();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getBegin() {
		return begin;
	}

	public void setBegin(Timestamp begin) {
		this.begin = begin;
	}

	public Timestamp getEnd() {
		return end;
	}

	public void setEnd(Timestamp end) {
		this.end = end;
	}

	public Person getWinner() {
		return winner;
	}

	public void setWinner(Person winner) {
		this.winner = winner;
	}

	public Person getLoser() {
		return loser;
	}

	public void setLoser(Person loser) {
		this.loser = loser;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((begin == null) ? 0 : begin.hashCode());
		result = prime * result + ((end == null) ? 0 : end.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((loser == null) ? 0 : loser.hashCode());
		result = prime * result + ((winner == null) ? 0 : winner.hashCode());
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
		MatchHistory other = (MatchHistory) obj;
		if (begin == null) {
			if (other.begin != null)
				return false;
		} else if (!begin.toString().equals(other.begin.toString()))
			return false;
		if (end == null) {
			if (other.end != null)
				return false;
		} else if (!end.toString().equals(other.end.toString()))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (loser == null) {
			if (other.loser != null)
				return false;
		} else if (!loser.equals(other.loser))
			return false;
		if (winner == null) {
			if (other.winner != null)
				return false;
		} else if (!winner.equals(other.winner))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MatchHistory [id=" + id + ", begin=" + begin + ", end=" + end + ", winner=" + winner + ", loser="
				+ loser + "]";
	}
	
	
}

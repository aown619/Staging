package com.revature.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="invite")
public class Invite {
	//id sender receiver game type status
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne @JoinColumn(name="sender_id")
	private Person sender;
	@ManyToOne @JoinColumn(name="receiver_id")
	private Person receiver;

//	@Column(name="sender_id")
//	private Integer senderId;
//	
//	@Column(name="receiver_id")
//	private Integer receiverId;
	
	@ManyToOne @JoinColumn(name="game_id")
	private Game game;
	
	@ManyToOne @JoinColumn(name="invite_type_id")
	private InviteType type;
	
	@ManyToOne @JoinColumn(name="invite_status_id")
	private InviteStatus status;
	
	public Invite()
	{
		id = -1;
		sender = new Person();
		receiver = new Person();
//		senderId = -1;
//		gameId = -1;
		game = new Game();
		type = new InviteType();
		status = new InviteStatus();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Person getSender() {
		return sender;
	}

	public void setSender(Person sender) {
		this.sender = sender;
	}

	public Person getReceiver() {
		return receiver;
	}

	public void setReceiver(Person receiver) {
		this.receiver = receiver;
	}
	
//	public Integer getSenderId() {
//		return senderId;
//	}
//
//	public void setSenderId(Integer senderId) {
//		this.senderId = senderId;
//	}
//
//	public Integer getReceiverId() {
//		return receiverId;
//	}
//
//	public void setReceiverId(Integer receiverId) {
//		this.receiverId = receiverId;
//	}

//	public Integer getGameId() {
//		return gameId;
//	}
//
//	public void setGameId(Integer gameId) {
//		this.gameId = gameId;
//	}
	
	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public InviteType getType() {
		return type;
	}

	public void setType(InviteType type) {
		this.type = type;
	}

	public InviteStatus getStatus() {
		return status;
	}

	public void setStatus(InviteStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Invite [id=" + id + ", sender=" + sender + ", receiver=" + receiver + ", game=" + game + ", type="
				+ type + ", status=" + status + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((game == null) ? 0 : game.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((receiver == null) ? 0 : receiver.hashCode());
		result = prime * result + ((sender == null) ? 0 : sender.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Invite other = (Invite) obj;
		if (game == null) {
			if (other.game != null)
				return false;
		} else if (!game.equals(other.game))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (receiver == null) {
			if (other.receiver != null)
				return false;
		} else if (!receiver.equals(other.receiver))
			return false;
		if (sender == null) {
			if (other.sender != null)
				return false;
		} else if (!sender.equals(other.sender))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
}

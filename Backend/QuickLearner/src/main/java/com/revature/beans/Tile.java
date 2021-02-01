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

@Entity
@Table
public class Tile {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="board_id")
	private Integer BoardId;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="tile_status_id")
	private TileStatus status;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="squid_id")
	private Squid calamari;
	@Column(name="x_pos")
	private Integer x;
	@Column(name="y_pos")
	private Integer y;
	
	public Tile()
	{
		id = -1;
		BoardId = -1;
		status = new TileStatus();
		calamari = new Squid();
		x=-1;
		y=-1;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Squid getCalamari() {
		return calamari;
	}

	public void setCalamari(Squid calamari) {
		this.calamari = calamari;
	}

	public Integer getBoardId() {
		return BoardId;
	}

	public void setBoardId(Integer boardId) {
		BoardId = boardId;
	}

	public TileStatus getStatus() {
		return status;
	}

	public void setStatus(TileStatus status) {
		this.status = status;
	}

	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((BoardId == null) ? 0 : BoardId.hashCode());
		result = prime * result + ((calamari == null) ? 0 : calamari.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((x == null) ? 0 : x.hashCode());
		result = prime * result + ((y == null) ? 0 : y.hashCode());
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
		Tile other = (Tile) obj;
		if (BoardId == null) {
			if (other.BoardId != null)
				return false;
		} else if (!BoardId.equals(other.BoardId))
			return false;
		if (calamari == null) {
			if (other.calamari != null)
				return false;
		} else if (!calamari.equals(other.calamari))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (x == null) {
			if (other.x != null)
				return false;
		} else if (!x.equals(other.x))
			return false;
		if (y == null) {
			if (other.y != null)
				return false;
		} else if (!y.equals(other.y))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Tile [id=" + id + ", BoardId=" + BoardId + ", status=" + status + ", calamari=" + calamari + ", x=" + x
				+ ", y=" + y + "]";
	}
	
	
}

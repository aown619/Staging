package com.revature.data;

import org.springframework.stereotype.Repository;

import com.revature.beans.TileStatus;

@Repository
public class TileStatusHibernate extends GenericHibernate<TileStatus> implements TileStatusDAO {

	public TileStatusHibernate() {
		super(TileStatus.class);
	}

}

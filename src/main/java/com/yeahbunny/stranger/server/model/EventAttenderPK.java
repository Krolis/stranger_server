package com.yeahbunny.stranger.server.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the event_attender database table.
 * 
 */
@Embeddable
public class EventAttenderPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_event", insertable=false, updatable=false)
	private long idEvent;

	@Column(name="id_user", insertable=false, updatable=false)
	private long idUser;

	public EventAttenderPK() {
	}
	public EventAttenderPK(Long idEvent, Long idUser) {
		this.idEvent = idEvent;
		this.idUser = idUser;
	}
	public long getIdEvent() {
		return this.idEvent;
	}
	public void setIdEvent(long idEvent) {
		this.idEvent = idEvent;
	}
	public long getIdUser() {
		return this.idUser;
	}
	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EventAttenderPK)) {
			return false;
		}
		EventAttenderPK castOther = (EventAttenderPK)other;
		return 
			(this.idEvent == castOther.idEvent)
			&& (this.idUser == castOther.idUser);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + Long.hashCode(this.idEvent);
		hash = hash * prime + Long.hashCode(this.idUser);
		
		return hash;
	}
}
package com.yeahbunny.stranger.server.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the event_attender database table.
 * 
 */
@Entity
@Table(name="event_attender")
@NamedQuery(name="EventAttender.findAll", query="SELECT e FROM EventAttender e")
public class EventAttender implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EventAttenderPK id;

	private String confirmed;

	@Column(name="rating")
	private Integer rating;

	//bi-directional many-to-one association to Event
	@ManyToOne
	@JoinColumn(name="id_event")
	@MapsId("idEvent")
	private Event event;

	//bi-directional many-to-one association to User
	@ManyToOne
	@MapsId("idUser")
	@JoinColumn(name="id_user")
	private User user;

	public EventAttender() {
	}

	public EventAttenderPK getId() {
		return this.id;
	}

	public void setId(EventAttenderPK id) {
		this.id = id;
	}

	public String getConfirmed() {
		return this.confirmed;
	}

	public void setConfirmed(String confirmed) {
		this.confirmed = confirmed;
	}

	public Integer getRating() {
		return this.rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public Event getEvent() {
		return this.event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
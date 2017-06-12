package com.yeahbunny.stranger.server.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


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
	
	private String consumed;

	@Column(name="rating")
	private Integer rating;

	@Column(name = "read_msg_timestamp")
	private Date readMessageTimestamp;

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
		this.readMessageTimestamp = new Date();
	}

	public EventAttender(Event event, User user) {
		this();
		this.id = new EventAttenderPK(event.getIdEvent(), user.getIdUser());
		this.event = event;
		this.user = user;
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

	public Date getReadMessageTimestamp() {
		return readMessageTimestamp;
	}

	public void setReadMessageTimestamp(Date readMessageTimestamp) {
		this.readMessageTimestamp = readMessageTimestamp;
	}

	public String getConsumed() {
		return consumed;
	}

	public void setConsumed(String consumed) {
		this.consumed = consumed;
	}
	
}
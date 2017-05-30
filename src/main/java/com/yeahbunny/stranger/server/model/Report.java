package com.yeahbunny.stranger.server.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the report database table.
 * 
 */
@Entity
@Table(name="report")
@NamedQuery(name="Report.findAll", query="SELECT r FROM Report r")
public class Report implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_report")
	private int idReport;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	private String description;

	//bi-directional many-to-one association to Event
	@ManyToOne
	@JoinColumn(name="id_event")
	private Event event;

	//bi-directional many-to-one association to EventMessage
	@ManyToOne
	@JoinColumn(name="id_message")
	private EventMessage eventMessage;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="id_user")
	private User user;

	public Report() {
	}

	public int getIdReport() {
		return this.idReport;
	}

	public void setIdReport(int idReport) {
		this.idReport = idReport;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Event getEvent() {
		return this.event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public EventMessage getEventMessage() {
		return this.eventMessage;
	}

	public void setEventMessage(EventMessage eventMessage) {
		this.eventMessage = eventMessage;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
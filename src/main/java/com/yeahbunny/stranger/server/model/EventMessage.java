package com.yeahbunny.stranger.server.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the event_message database table.
 * 
 */
@Entity
@Table(name="event_message")
@NamedQuery(name="EventMessage.findAll", query="SELECT e FROM EventMessage e")
public class EventMessage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_message")
	private int idMessage;

	private String content;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date date;

	//bi-directional many-to-one association to Event
	@ManyToOne
	@JoinColumn(name="id_event")
	private Event event;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="id_user")
	private User user;

	//bi-directional many-to-one association to Report
	@OneToMany(mappedBy="eventMessage")
	private Set<Report> reports;

	public EventMessage() {
		super();
		this.date = new Date();
	}

	public EventMessage(Event event, User user, String content) {
		this();
		this.content = content;
		this.event = event;
		this.user = user;
	}



	public int getIdMessage() {
		return this.idMessage;
	}

	public void setIdMessage(int idMessage) {
		this.idMessage = idMessage;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
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

	public Set<Report> getReports() {
		return this.reports;
	}

	public void setReports(Set<Report> reports) {
		this.reports = reports;
	}

	public Report addReport(Report report) {
		getReports().add(report);
		report.setEventMessage(this);

		return report;
	}

	public Report removeReport(Report report) {
		getReports().remove(report);
		report.setEventMessage(null);

		return report;
	}

}
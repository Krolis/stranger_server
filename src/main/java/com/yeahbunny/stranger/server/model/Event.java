package com.yeahbunny.stranger.server.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;

import com.yeahbunny.stranger.server.controller.dto.response.EventType;


/**
 * The persistent class for the event database table.
 * 
 */
@Entity
@Table(name="event")
@NamedQuery(name="Event.findAll", query="SELECT e FROM Event e")
public class Event implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_event")
	private long idEvent;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_end")
	private Date dateEnd;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_start")
	private Date dateStart;

	private double latitude;

	private double longitude;

	@Column(name="max_attenders")
	private int maxAttenders;
	
	@Column(name="title")
	private String title;
	
	@Column(name="details")
	private String details;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="id_user_organizer")
	private User user;

	//bi-directional many-to-one association to EventAttender
//	@OneToMany(mappedBy="event")
//	private Set<EventAttender> eventAttenders;

	//bi-directional many-to-one association to EventMessage
	@OneToMany(mappedBy="event")
	private Set<EventMessage> eventMessages;

	//bi-directional many-to-one association to Report
	@OneToMany(mappedBy="event")
	private Set<Report> reports;

	public Event() {
	}
	
	public EventType checkEventType() {
    	Date curDate = new Date();
    	// TODO - obsługa braków dat?? - not nulle w bazie
    	if (curDate.before(dateStart))
    		return EventType.FUTURE;
    	else if (curDate.before(dateEnd))
			return EventType.NOW;
    	else
    		return EventType.HISTORIC;
	}

	public long getIdEvent() {
		return this.idEvent;
	}

	public void setIdEvent(long idEvent) {
		this.idEvent = idEvent;
	}

	public Date getDateEnd() {
		return this.dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	public Date getDateStart() {
		return this.dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public int getMaxAttenders() {
		return this.maxAttenders;
	}

	public void setMaxAttenders(int maxAttenders) {
		this.maxAttenders = maxAttenders;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}
/*
	public Set<EventAttender> getEventAttenders() {
		return this.eventAttenders;
	}

	public void setEventAttenders(Set<EventAttender> eventAttenders) {
		this.eventAttenders = eventAttenders;
	}

	public EventAttender addEventAttender(EventAttender eventAttender) {
		getEventAttenders().add(eventAttender);
		eventAttender.setEvent(this);

		return eventAttender;
	}

	public EventAttender removeEventAttender(EventAttender eventAttender) {
		getEventAttenders().remove(eventAttender);
		eventAttender.setEvent(null);

		return eventAttender;
	}
*/
	public Set<EventMessage> getEventMessages() {
		return this.eventMessages;
	}

	public void setEventMessages(Set<EventMessage> eventMessages) {
		this.eventMessages = eventMessages;
	}

	public EventMessage addEventMessage(EventMessage eventMessage) {
		getEventMessages().add(eventMessage);
		eventMessage.setEvent(this);

		return eventMessage;
	}

	public EventMessage removeEventMessage(EventMessage eventMessage) {
		getEventMessages().remove(eventMessage);
		eventMessage.setEvent(null);

		return eventMessage;
	}

	public Set<Report> getReports() {
		return this.reports;
	}

	public void setReports(Set<Report> reports) {
		this.reports = reports;
	}

	public Report addReport(Report report) {
		getReports().add(report);
		report.setEvent(this);

		return report;
	}

	public Report removeReport(Report report) {
		getReports().remove(report);
		report.setEvent(null);

		return report;
	}

}
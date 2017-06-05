package com.yeahbunny.stranger.server.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Optional;
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
import javax.persistence.Transient;

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
	private Long idEvent;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_end")
	private Date dateEnd;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_start")
	private Date dateStart;

	private Double latitude;

	private Double longitude;

	@Column(name="max_attenders")
	private int maxAttenders;
	
	@Column(name="title")
	private String title;
	
	@Column(name="details")
	private String details;

	@Column(name = "unread_msg")
	private int unreadedMessages;

	@Column(name = "read_msg_timestamp")
	private Date readMessageTimestamp;
	
	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="id_user_organizer")
	private User creator;

	//bi-directional many-to-one association to EventAttender
	@OneToMany(mappedBy="event")
	private Set<EventAttender> eventAttenders;

	//bi-directional many-to-one association to EventMessage
	@OneToMany(mappedBy="event")
	private Set<EventMessage> eventMessages;

	//bi-directional many-to-one association to Report
	@OneToMany(mappedBy="event")
	private Set<Report> reports;

	public Event() {
		this.readMessageTimestamp = new Date();
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
	
	@Transient
	public EventAttender getEventAttender(User user) {
		if (getEventAttenders() != null){
			Optional<EventAttender> optional = getEventAttenders().stream().filter(evAt -> evAt.getUser().equals(user)).findFirst();
			if(optional.isPresent()){
				return optional.get();
			}else{
				return null;
			}
		}else{
			return null;
		}
	}

	public Long getIdEvent() {
		return this.idEvent;
	}

	public void setIdEvent(Long idEvent) {
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

	public Double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Integer getMaxAttenders() {
		return this.maxAttenders;
	}

	public void setMaxAttenders(Integer maxAttenders) {
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

	public User getCreator() {
		return this.creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

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

	public int getUnreadedMessages() {
		return unreadedMessages;
	}

	public void setUnreadedMessages(int unreadedMessages) {
		this.unreadedMessages = unreadedMessages;
	}

	public Date getReadMessageTimestamp() {
		return readMessageTimestamp;
	}

	public void setReadMessageTimestamp(Date readMessageTimestamp) {
		this.readMessageTimestamp = readMessageTimestamp;
	}

	public void setMaxAttenders(int maxAttenders) {
		this.maxAttenders = maxAttenders;
	}
	
	
}
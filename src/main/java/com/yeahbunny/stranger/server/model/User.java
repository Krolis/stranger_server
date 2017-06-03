package com.yeahbunny.stranger.server.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@Table(name="user")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_user")
	private Long idUser;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="birthdate")
	private Date birthdate;
	
	@Column(name="gender")
	private String gender;

	@Column(name="hashed_pw")
	private String hashedPw;

	@Column(name="login")
	private String login;

	@Column(name="name")
	private String name;

	@Column(name="surname")
	private String surname;
	
	@Column(name="photo_url")
	private String photoUrl;

	//bi-directional many-to-one association to Event
	@OneToMany(mappedBy="creator")
	private Set<Event> events;

	//bi-directional many-to-one association to EventAttender
	@OneToMany(mappedBy="user")
	private Set<EventAttender> eventAttenders;

	//bi-directional many-to-one association to EventMessage
	@OneToMany(mappedBy="user")
	private Set<EventMessage> eventMessages;

	//bi-directional many-to-one association to Report
	@OneToMany(mappedBy="user")
	private Set<Report> reports;

	public User() {
	}

	public User(String login, String hashedPw, boolean isFemale, Date birthdate) {
		super();
		this.login = login;
		this.hashedPw = hashedPw;
		this.birthdate = birthdate;
		if (isFemale)
			this.gender = "F";
		else
			this.gender = "M";
	} 
	
	//getters
	
	public Long getIdUser() {
		return this.idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getHashedPw() {
		return this.hashedPw;
	}

	public void setHashedPw(String hashedPw) {
		this.hashedPw = hashedPw;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public Set<Event> getEvents() {
		return this.events;
	}

	public void setEvents(Set<Event> events) {
		this.events = events;
	}

	public Event addEvent(Event event) {
		getEvents().add(event);
		event.setCreator(this);

		return event;
	}

	public Event removeEvent(Event event) {
		getEvents().remove(event);
		event.setCreator(null);

		return event;
	}

	public Set<EventAttender> getEventAttenders() {
		return this.eventAttenders;
	}

	public void setEventAttenders(Set<EventAttender> eventAttenders) {
		this.eventAttenders = eventAttenders;
	}

	public EventAttender addEventAttender(EventAttender eventAttender) {
		getEventAttenders().add(eventAttender);
		eventAttender.setUser(this);

		return eventAttender;
	}

	public EventAttender removeEventAttender(EventAttender eventAttender) {
		getEventAttenders().remove(eventAttender);
		eventAttender.setUser(null);

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
		eventMessage.setUser(this);

		return eventMessage;
	}

	public EventMessage removeEventMessage(EventMessage eventMessage) {
		getEventMessages().remove(eventMessage);
		eventMessage.setUser(null);

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
		report.setUser(this);

		return report;
	}

	public Report removeReport(Report report) {
		getReports().remove(report);
		report.setUser(null);

		return report;
	}

    public int calculateAge() {
    	int age = 0;
    	try {
    		age = getDiffYears(birthdate, new Date());
    	} catch (Exception e) {
    		
    	}
		return age;
	}

    private int getDiffYears(Date first, Date last) {
        Calendar a = getCalendar(first);
        Calendar b = getCalendar(last);
        int diff = b.get(Calendar.YEAR) - a.get(Calendar.YEAR);
        if (a.get(Calendar.DAY_OF_YEAR) > b.get(Calendar.DAY_OF_YEAR)) {
            diff--;
        }
        return diff;
    }
    
    private Calendar getCalendar(Date date) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTime(date);
        return cal;
    }
	
}
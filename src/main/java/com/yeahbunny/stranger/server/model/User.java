package com.yeahbunny.stranger.server.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


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
	private long idUser;

	private int age;

	private String gender;

	@Column(name="hashed_pw")
	private String hashedPw;

	private String login;

	private String name;

	private String surname;

	//bi-directional many-to-one association to Event
	@OneToMany(mappedBy="user")
	private Set<Event> events;

	//bi-directional many-to-one association to EventAttender
	//@OneToMany(mappedBy="user")
//	private Set<EventAttender> eventAttenders;

	//bi-directional many-to-one association to EventMessage
	@OneToMany(mappedBy="user")
	private Set<EventMessage> eventMessages;

	//bi-directional many-to-one association to Report
	@OneToMany(mappedBy="user")
	private Set<Report> reports;

	public User() {
	}

	public long getIdUser() {
		return this.idUser;
	}

	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}

	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
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

	public Set<Event> getEvents() {
		return this.events;
	}

	public void setEvents(Set<Event> events) {
		this.events = events;
	}

	public Event addEvent(Event event) {
		getEvents().add(event);
		event.setUser(this);

		return event;
	}

	public Event removeEvent(Event event) {
		getEvents().remove(event);
		event.setUser(null);

		return event;
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
		eventAttender.setUser(this);

		return eventAttender;
	}

	public EventAttender removeEventAttender(EventAttender eventAttender) {
		getEventAttenders().remove(eventAttender);
		eventAttender.setUser(null);

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

}
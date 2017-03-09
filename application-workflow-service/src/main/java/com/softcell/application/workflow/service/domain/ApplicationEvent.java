package com.softcell.application.workflow.service.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * This is immutable class which represents an event that can happen with the application
 * @author Ghanasham
 *
 */
@Entity
public final class ApplicationEvent {

	public enum EventType{
		CREATED,
		APPROVED,
		REJECTED,
		CANCELLED
	}
	
	public enum EventCreatorType{
		SYSTEM,
		APPLICANT,
		MANAGER
	}
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable = false)
	private EventType eventType;
	
	@Column(nullable = false)
	private EventCreatorType eventCreatorType;
	
	@Column(nullable = false)
	private Long eventCreatorId;
	
	private String comments;
	
	@Column(nullable = false)
	private LocalDateTime createTimeStamp;
	
	private ApplicationEvent(){}
	
	public ApplicationEvent(EventType eventType, EventCreatorType eventCreatorType, Long eventCreatorId, String comments){
		
		this.eventType = eventType;
		this.eventCreatorType = eventCreatorType;
		this.eventCreatorId = eventCreatorId;
		this.comments = comments;
		
	}

	public long getId() {
		return id;
	}

	public EventType getEventType() {
		return eventType;
	}

	public EventCreatorType getEventCreatorType() {
		return eventCreatorType;
	}

	public Long getEventCreatorId() {
		return eventCreatorId;
	}

	public String getComments() {
		return comments;
	}

	public LocalDateTime getCreateTimeStamp() {
		return createTimeStamp;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException("This is immutable object");
	}
}

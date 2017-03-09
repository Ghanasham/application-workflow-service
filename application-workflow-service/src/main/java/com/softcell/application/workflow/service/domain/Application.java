package com.softcell.application.workflow.service.domain;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.softcell.application.workflow.service.domain.ApplicationEvent.EventCreatorType;
import com.softcell.application.workflow.service.domain.ApplicationEvent.EventType;

/**
 * This class represents abstract application which should be extended by specific applications such as Home Loan application, Car Loan application etc
 * @author Ghanasham
 *
 */
@Entity
public abstract class Application {

	/**
	 * Different types of Application states
	 * @author Ghanasham
	 *
	 */
	public enum State{
		NEW,
		IN_PROCESS,
		APPROVED,
		REJECTED,
		APPLICANT_ACTION_REQUIRED
	}
	
	public enum ApplicationType{
		HOME_LOAN,
		CAR_LOAN
	}
	
	/**
	 * Primary key of the application
	 */
	@Id
	@GeneratedValue
	private long id;
	
	/**
	 * orgId of this application
	 * 
	 */
	@Column(nullable = false)
	private Long orgId;
	
	/**
	 * Current level of the application. Default level is 1 which represents the first level in approval chain.
	 */
	@Column(nullable = false)
	private byte level = 1;
	
	/**
	 * Max number of levels this application will go through
	 */
	@Column(nullable = false)
	private byte maxLevels;
	
	/**
	 * Current State of the application
	 */
	private State state = State.NEW;
	
	/**
	 * Person who has applied
	 */
	@ManyToOne
	private Applicant applicant;
	
	@Column(nullable = false)
	protected ApplicationType applicationType; 
	
	/**
	 * Employee id of the assignee manager. Manager details will be provided by admin-service
	 * If application is rejected by first level manager, it will be assigned to Applicant.
	 */
	@Column(nullable = false)
	private Long assigneeId;
	
	/**
	 * Application create time stamp
	 */
	private final LocalDateTime createTimeStamp = LocalDateTime.now();
	
	/**
	 * List of events happened to this application.
	 */
	@OneToMany
	private List<ApplicationEvent> events;
	
	public void approve(String comment){
		
		if(level >= 1 && level < maxLevels){
			
			level++;
			state = State.IN_PROCESS;

			events.add(new ApplicationEvent(EventType.APPROVED, EventCreatorType.MANAGER, assigneeId, comment));
		}else if(level == maxLevels){
			state = State.APPROVED;
			
			events.add(new ApplicationEvent(EventType.APPROVED, EventCreatorType.MANAGER, assigneeId, comment));
		}
	}
	
	public void reject(String comment){
		
		//Newly submitted application being rejected by first level of manager.
		if(level == 1){
			
			//level 0 indicate that application is not assigned to any manager and is pending for action from applicant.
			level = 0;
			state = State.APPLICANT_ACTION_REQUIRED;
			
			//Add rejected event to the list
			events.add(new ApplicationEvent(EventType.REJECTED, EventCreatorType.MANAGER, assigneeId, comment));
			
			//Assign application back to Applicant
			assigneeId = applicant.getId();
			
		}else if(level > 1){
			
			level--;
			
			//Add rejected event to the list
			events.add(new ApplicationEvent(EventType.REJECTED, EventCreatorType.MANAGER, assigneeId, comment));
		}
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public byte getLevel() {
		return level;
	}

	public State getState() {
		return state;
	}

	public LocalDateTime getCreateTimeStamp() {
		return createTimeStamp;
	}

	public List<ApplicationEvent> getEvents() {
		return events;
	}

	public ApplicationType getApplicationType() {
		return applicationType;
	}

	public void setApplicationType(ApplicationType applicationType) {
		this.applicationType = applicationType;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Applicant getApplicant() {
		return applicant;
	}

	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
	}

	public Long getAssigneeId() {
		return assigneeId;
	}

	public void setAssigneeId(Long assigneeId) {
		this.assigneeId = assigneeId;
	}
}

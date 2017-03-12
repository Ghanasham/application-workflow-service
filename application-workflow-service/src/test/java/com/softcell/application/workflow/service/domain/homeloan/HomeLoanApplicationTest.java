package com.softcell.application.workflow.service.domain.homeloan;

import static org.junit.Assert.*;
import org.junit.Test;

import com.softcell.application.workflow.service.domain.Applicant;
import com.softcell.application.workflow.service.domain.Application.State;
import com.softcell.application.workflow.service.domain.ApplicationEvent.EventCreatorType;
import com.softcell.application.workflow.service.domain.ApplicationEvent.EventType;

public class HomeLoanApplicationTest {

	@Test
	public void testMidLevelApprove(){
		
		HomeLoanApplication app = new HomeLoanApplication();
		app.setAssigneeId(1L);
		app.setMaxLevels((byte)4);
		app.approve("Approved");
		
		assertEquals(State.IN_PROCESS, app.getState());
		assertEquals("Approved", app.getEvents().get(0).getComments());
		assertEquals(EventCreatorType.MANAGER, app.getEvents().get(0).getEventCreatorType());
		assertEquals(EventType.APPROVED, app.getEvents().get(0).getEventType());
		assertEquals((Long)1L, app.getEvents().get(0).getEventCreatorId());
		assertEquals(2,app.getLevel());
	}
	
	@Test
	public void testLastLevelApprove(){
		
		HomeLoanApplication app = new HomeLoanApplication();
		app.setAssigneeId(1L);
		app.setMaxLevels((byte)1);
		app.approve("Approved");
		
		assertEquals(State.APPROVED, app.getState());
		assertEquals("Approved", app.getEvents().get(0).getComments());
		assertEquals(EventCreatorType.MANAGER, app.getEvents().get(0).getEventCreatorType());
		assertEquals(EventType.APPROVED, app.getEvents().get(0).getEventType());
		assertEquals((Long)1L, app.getEvents().get(0).getEventCreatorId());
		assertEquals(1,app.getLevel());
	}
	
	@Test
	public void testFirstLevelReject(){
		
		Applicant applicant = new Applicant();
		applicant.setId(444L);
		
		HomeLoanApplication app = new HomeLoanApplication();
		app.setAssigneeId(1L);
		app.setMaxLevels((byte)3);
		app.setApplicant(applicant);
		app.reject("Rejected");
		
		assertEquals(State.APPLICANT_ACTION_REQUIRED, app.getState());
		assertEquals("Rejected", app.getEvents().get(0).getComments());
		assertEquals(EventCreatorType.MANAGER, app.getEvents().get(0).getEventCreatorType());
		assertEquals(EventType.REJECTED, app.getEvents().get(0).getEventType());
		assertEquals((Long)1L, app.getEvents().get(0).getEventCreatorId());
		assertEquals(app.getApplicant().getId(), app.getAssigneeId());
		assertEquals(0,app.getLevel());
	}
	
	@Test
	public void testMidLevelReject(){
		
		Applicant applicant = new Applicant();
		applicant.setId(444L);
		
		HomeLoanApplication app = new HomeLoanApplication();
		app.setAssigneeId(1L);
		app.setMaxLevels((byte)3);
		app.setApplicant(applicant);
		app.approve("Approved");
		app.approve("Approved");
		app.reject("Rejected");
		
		assertEquals(State.IN_PROCESS, app.getState());
		assertEquals(3, app.getEvents().size());
		assertEquals(EventCreatorType.MANAGER, app.getEvents().get(0).getEventCreatorType());
		assertEquals(EventType.REJECTED, app.getEvents().get(2).getEventType());
		assertEquals((Long)1L, app.getEvents().get(2).getEventCreatorId());
		assertEquals(2,app.getLevel());
	}
}

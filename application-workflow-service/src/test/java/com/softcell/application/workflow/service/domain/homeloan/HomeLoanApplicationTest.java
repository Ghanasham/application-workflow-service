package com.softcell.application.workflow.service.domain.homeloan;

import static org.junit.Assert.*;
import org.junit.Test;

import com.softcell.application.workflow.service.domain.Application.State;
import com.softcell.application.workflow.service.domain.ApplicationEvent.EventCreatorType;
import com.softcell.application.workflow.service.domain.ApplicationEvent.EventType;

public class HomeLoanApplicationTest {

	@Test
	public void testMidLevelApprove(){
		
		HomeLoanApplication app = new HomeLoanApplication();
		app.setAssigneeId(1L);
		app.approve("Approved");
		
		assertEquals(State.IN_PROCESS, app.getState());
		assertEquals("Approved", app.getEvents().get(0).getComments());
		assertEquals(EventCreatorType.MANAGER, app.getEvents().get(0).getEventCreatorType());
		assertEquals(EventType.APPROVED, app.getEvents().get(0).getEventType());
		assertEquals((Long)1L, app.getEvents().get(0).getEventCreatorId());
	}
}

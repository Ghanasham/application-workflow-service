package com.softcell.application.workflow.service.integration;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.softcell.application.workflow.service.domain.Applicant;
import com.softcell.application.workflow.service.domain.CommandComment;
import com.softcell.application.workflow.service.domain.CommandComment.Command;
import com.softcell.application.workflow.service.domain.homeloan.HomeLoanApplication;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MainWorkflowIntegrationTest {

	@Autowired
    private TestRestTemplate restTemplate;
	
	private HomeLoanApplication app1 = new HomeLoanApplication();
	private HomeLoanApplication app2 = new HomeLoanApplication();
	private HomeLoanApplication app3 = new HomeLoanApplication();
	private HomeLoanApplication app4 = new HomeLoanApplication();
	private HomeLoanApplication app5 = new HomeLoanApplication();
	
	@Before
	public void createNewHomeApplication(){
		Applicant applicant = new Applicant();
		applicant.setId(1L);
		
		initApplication(app1, applicant);
		initApplication(app2, applicant);
		initApplication(app3, applicant);
		initApplication(app4, applicant);
		initApplication(app5, applicant);
		
	}
	
	@Test
	public void testWorkflow(){
		
		//Submit 5 applications
		app1 = restTemplate.postForObject("http://localhost:8080/home-loan/application", app1, HomeLoanApplication.class);
		assertEquals((Long)12341L, app1.getAssigneeId());
		
		app2 = restTemplate.postForObject("http://localhost:8080/home-loan/application", app2, HomeLoanApplication.class);
		assertEquals((Long)12342L, app2.getAssigneeId());
		
		app3 = restTemplate.postForObject("http://localhost:8080/home-loan/application", app3, HomeLoanApplication.class);
		assertEquals((Long)12343L, app3.getAssigneeId());
		
		app4 = restTemplate.postForObject("http://localhost:8080/home-loan/application", app4, HomeLoanApplication.class);
		assertEquals((Long)12344L, app4.getAssigneeId());
		
		app5 = restTemplate.postForObject("http://localhost:8080/home-loan/application", app5, HomeLoanApplication.class);
		assertEquals((Long)12341L, app5.getAssigneeId());
		
		//Approve 5 applications and check if they go to level 2
		
		restTemplate.put("http://localhost:8080/manage/application/" + app1.getId(), new CommandComment(Command.APPROVE, "approved"));
		assertEquals((Long)12345L, restTemplate.getForObject("http://localhost:8080/manage/application/" + app1.getId(), HomeLoanApplication.class).getAssigneeId());
		
		restTemplate.put("http://localhost:8080/manage/application/" + app2.getId(), new CommandComment(Command.APPROVE, "approved"));
		assertEquals((Long)12346L, restTemplate.getForObject("http://localhost:8080/manage/application/" + app2.getId(), HomeLoanApplication.class).getAssigneeId());
		
		restTemplate.put("http://localhost:8080/manage/application/" + app3.getId(), new CommandComment(Command.APPROVE, "approved"));
		assertEquals((Long)12347L, restTemplate.getForObject("http://localhost:8080/manage/application/" + app3.getId(), HomeLoanApplication.class).getAssigneeId());
		
		restTemplate.put("http://localhost:8080/manage/application/" + app4.getId(), new CommandComment(Command.APPROVE, "approved"));
		assertEquals((Long)12345L, restTemplate.getForObject("http://localhost:8080/manage/application/" + app4.getId(), HomeLoanApplication.class).getAssigneeId());
		
		restTemplate.put("http://localhost:8080/manage/application/" + app5.getId(), new CommandComment(Command.APPROVE, "approved"));
		assertEquals((Long)12346L, restTemplate.getForObject("http://localhost:8080/manage/application/" + app5.getId(), HomeLoanApplication.class).getAssigneeId());
		
		//Approve 5 applications and check if they go to level 3
		
		restTemplate.put("http://localhost:8080/manage/application/" + app1.getId(), new CommandComment(Command.APPROVE, "approved"));
		assertEquals((Long)12348L, restTemplate.getForObject("http://localhost:8080/manage/application/" + app1.getId(), HomeLoanApplication.class).getAssigneeId());
		
		restTemplate.put("http://localhost:8080/manage/application/" + app2.getId(), new CommandComment(Command.APPROVE, "approved"));
		assertEquals((Long)12349L, restTemplate.getForObject("http://localhost:8080/manage/application/" + app2.getId(), HomeLoanApplication.class).getAssigneeId());
		
		restTemplate.put("http://localhost:8080/manage/application/" + app3.getId(), new CommandComment(Command.APPROVE, "approved"));
		assertEquals((Long)12348L, restTemplate.getForObject("http://localhost:8080/manage/application/" + app3.getId(), HomeLoanApplication.class).getAssigneeId());
		
		restTemplate.put("http://localhost:8080/manage/application/" + app4.getId(), new CommandComment(Command.APPROVE, "approved"));
		assertEquals((Long)12349L, restTemplate.getForObject("http://localhost:8080/manage/application/" + app4.getId(), HomeLoanApplication.class).getAssigneeId());
		
		restTemplate.put("http://localhost:8080/manage/application/" + app5.getId(), new CommandComment(Command.APPROVE, "approved"));
		assertEquals((Long)12348L, restTemplate.getForObject("http://localhost:8080/manage/application/" + app5.getId(), HomeLoanApplication.class).getAssigneeId());
		
		//Approve 5 applications and check if they go to level 4
		
		restTemplate.put("http://localhost:8080/manage/application/" + app1.getId(), new CommandComment(Command.APPROVE, "approved"));
		assertEquals((Long)12350L, restTemplate.getForObject("http://localhost:8080/manage/application/" + app1.getId(), HomeLoanApplication.class).getAssigneeId());
		
		restTemplate.put("http://localhost:8080/manage/application/" + app2.getId(), new CommandComment(Command.APPROVE, "approved"));
		assertEquals((Long)12350L, restTemplate.getForObject("http://localhost:8080/manage/application/" + app2.getId(), HomeLoanApplication.class).getAssigneeId());
		
		restTemplate.put("http://localhost:8080/manage/application/" + app3.getId(), new CommandComment(Command.APPROVE, "approved"));
		assertEquals((Long)12350L, restTemplate.getForObject("http://localhost:8080/manage/application/" + app3.getId(), HomeLoanApplication.class).getAssigneeId());
		
		restTemplate.put("http://localhost:8080/manage/application/" + app4.getId(), new CommandComment(Command.APPROVE, "approved"));
		assertEquals((Long)12350L, restTemplate.getForObject("http://localhost:8080/manage/application/" + app4.getId(), HomeLoanApplication.class).getAssigneeId());
		
		restTemplate.put("http://localhost:8080/manage/application/" + app5.getId(), new CommandComment(Command.APPROVE, "approved"));
		assertEquals((Long)12350L, restTemplate.getForObject("http://localhost:8080/manage/application/" + app5.getId(), HomeLoanApplication.class).getAssigneeId());
	}
	
	private void initApplication(HomeLoanApplication app, Applicant applicant){
		
		app.setApplicant(applicant);
		app.setLoanAmount(100000);
		app.setOrgId(1L);
		app.setTenure((byte)10);
	}
}

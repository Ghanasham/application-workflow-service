package com.softcell.application.workflow.service.integration;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.softcell.application.workflow.service.domain.Applicant;
import com.softcell.application.workflow.service.domain.homeloan.HomeLoanApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationControllerIntegrationTest {

	@Autowired
    private TestRestTemplate restTemplate;

	@Test
	public void testCreateApplication() throws Exception {
		
		Applicant applicant = new Applicant();
		applicant.setId(1L);
		
		HomeLoanApplication hlApplication = new HomeLoanApplication();
		hlApplication.setOrgId(1L);
		hlApplication.setApplicant(applicant);
		hlApplication.setAssigneeId(1L);
		hlApplication.setLoanAmount(100);
		hlApplication.setTenure((byte)25);
    	
		HomeLoanApplication postHomeLoanApplication = restTemplate.postForObject("http://localhost:8080/home-loan/application", hlApplication, HomeLoanApplication.class);
    	
		HomeLoanApplication getHomeLoanApplication = restTemplate.getForObject("http://localhost:8080/home-loan/application/" + postHomeLoanApplication.getId() , HomeLoanApplication.class);
    	
    	Assert.assertEquals(getHomeLoanApplication.getTenure(), postHomeLoanApplication.getTenure());
	}

	
}

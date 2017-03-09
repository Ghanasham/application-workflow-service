package com.softcell.application.workflow.service.integration;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;


import com.softcell.application.workflow.service.domain.Applicant;
import com.softcell.application.workflow.service.domain.ContactDetails;
import com.softcell.application.workflow.service.domain.ContactDetails.State;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicantControllerIntegrationTest {

	@Autowired
    private TestRestTemplate restTemplate;

	@Test
	public void testCreateApplicant() throws Exception {
		
		ContactDetails contact = new ContactDetails();
    	contact.setAddressLine1("Addr1");
    	contact.setAddressLine2("Addr2");
    	contact.setCity("Pune");
    	contact.setEmail("test@gmail.com");
    	contact.setPhone("1234567899");
    	contact.setPinCode(411027);
    	contact.setState(State.Maharashtra);
    	
		
		Applicant applicant = new Applicant();
		applicant.setName("Ghanasham Lavand");
		applicant.setContactDetails(contact);
		
		Applicant postApplicant = restTemplate.postForObject("http://localhost:8080/register/applicant", applicant, Applicant.class);
    	
		Applicant getApplicant = restTemplate.getForObject("http://localhost:8080/register/applicant/" + postApplicant.getId() , Applicant.class);
    	
    	Assert.assertEquals(getApplicant.getName(), postApplicant.getName());
	}
	
}

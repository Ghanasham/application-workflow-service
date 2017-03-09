package com.softcell.application.workflow.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.softcell.application.workflow.service.domain.Applicant;
import com.softcell.application.workflow.service.service.ApplicantService;

@RestController
@RequestMapping(path = "/register/applicant")
public class ApplicantController {

	@Autowired
	private ApplicantService applicantService;

	@RequestMapping(path = "/{applicantId}", method = RequestMethod.GET)
	public Applicant getApplicant(@PathVariable Long applicantId){

		return applicantService.getApplicant(applicantId);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Applicant createApplicant(@RequestBody Applicant applicant){

		return applicantService.saveApplicant(applicant);
	}
	
	@RequestMapping(path = "/{applicantId}", method = RequestMethod.PUT)
	public Applicant updateApplicant(@RequestBody Applicant applicant){
		
		return applicantService.saveApplicant(applicant);
	}
	
	@RequestMapping(path = "/{applicantId}", method = RequestMethod.DELETE)
	public void deleteApplicant(@PathVariable Long applicantId){

		applicantService.deleteApplicant(applicantId);
	}
}

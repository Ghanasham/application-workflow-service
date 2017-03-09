package com.softcell.application.workflow.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.softcell.application.workflow.service.domain.Application;
import com.softcell.application.workflow.service.domain.homeloan.HomeLoanApplication;
import com.softcell.application.workflow.service.service.ApplicationService;

@RestController
@RequestMapping(path = "/home-loan/application")
public class HomeLoanApplicationController {

	@Autowired
	private ApplicationService applicationService;

	@RequestMapping(path = "/{applicationId}", method = RequestMethod.GET)
	public Application getApplication(@PathVariable Long applicationId){

		return applicationService.getApplication(applicationId);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Application createApplication(@RequestBody HomeLoanApplication application){

		return applicationService.saveApplication(application);
	}
	
	@RequestMapping(path = "/{applicationId}", method = RequestMethod.PUT)
	public Application updateApplication(@RequestBody HomeLoanApplication application){
		
		return applicationService.saveApplication(application);
	}
	
	@RequestMapping(path = "/{applicationId}", method = RequestMethod.DELETE)
	public void deleteApplication(@PathVariable Long applicationId){
		applicationService.deleteApplication(applicationId);
	}
}

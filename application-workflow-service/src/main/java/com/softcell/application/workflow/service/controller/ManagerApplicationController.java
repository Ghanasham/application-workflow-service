package com.softcell.application.workflow.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.softcell.application.workflow.service.domain.Application;
import com.softcell.application.workflow.service.domain.CommandComment;
import com.softcell.application.workflow.service.service.ApplicationService;

@RestController
@RequestMapping(path = "/manage/application")
public class ManagerApplicationController {

	@Autowired
	private ApplicationService applicationService;

	@RequestMapping(path = "/{applicationId}", method = RequestMethod.GET)
	public Application getApplication(@PathVariable Long applicationId){

		return applicationService.getApplication(applicationId);
	}
	
	@RequestMapping(path = "/{applicationId}", method = RequestMethod.PUT)
	public void manageApplication(@PathVariable Long applicationId, @RequestBody CommandComment CommandComment){
		
		applicationService.manageApplication(applicationId, CommandComment);
	}
	
	
	
}

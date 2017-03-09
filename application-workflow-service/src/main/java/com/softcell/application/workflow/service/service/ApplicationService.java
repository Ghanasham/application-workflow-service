package com.softcell.application.workflow.service.service;

import com.softcell.application.workflow.service.domain.Application;
import com.softcell.application.workflow.service.domain.CommandComment;

public interface ApplicationService {

	public Application getApplication(Long id);
	
	public Application saveApplication(Application application);
	
	public void deleteApplication(Long id);
	
	public void manageApplication(Long id, CommandComment commandComment);
	
	
}

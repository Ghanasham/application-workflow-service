package com.softcell.application.workflow.service.service.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.softcell.application.workflow.service.service.ApplicationService;
import com.softcell.application.workflow.service.domain.Application;
import com.softcell.application.workflow.service.domain.CommandComment;
import com.softcell.application.workflow.service.repo.ApplicationRepository;

@Service
public class DBApplicationService implements ApplicationService{

	@Autowired
	ApplicationRepository applicationRepo;
	
	@Autowired
    private RestTemplate restTemplate;

	
	@Override
	public Application getApplication(Long id) {
		
		return applicationRepo.findOne(id);
	}

	@Override
	public Application saveApplication(Application application) {

		reAssignManager(application);
		return applicationRepo.save(application);
	}

	@Override
	public void deleteApplication(Long id) {

		applicationRepo.delete(id);
	}

	@Override
	public void manageApplication(Long id, CommandComment commandComment) {
		
		Application application = this.getApplication(id);
		
		if(commandComment.getCommand() == CommandComment.Command.APPROVE)
			application.approve(commandComment.getComment());
		else if(commandComment.getCommand() == CommandComment.Command.REJECT)
			application.reject(commandComment.getComment());
		
		reAssignManager(application);
	}
	
	private void reAssignManager(Application application){
		
		//Get the next manager's employee id to assign this application to him
		Long assigneeManagerEmployeeId = restTemplate.getForObject("http://localhost:8080/admin/manager/next-manager/" 
		+ application.getOrgId() + "/" + application.getApplicationType()
		+ "/" + application.getLevel(), Long.class);
		
		application.setAssigneeId(assigneeManagerEmployeeId);
				
	}

}

package com.softcell.application.workflow.service.service.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softcell.application.workflow.service.service.ApplicationService;
import com.softcell.application.workflow.service.domain.Application;
import com.softcell.application.workflow.service.repo.ApplicationRepository;

@Service
public class DBApplicationService implements ApplicationService{

	@Autowired
	ApplicationRepository applicationRepo;
	
	@Override
	public Application getApplication(Long id) {
		
		return applicationRepo.findOne(id);
	}

	@Override
	public Application saveApplication(Application application) {

		return applicationRepo.save(application);
	}

	@Override
	public void deleteApplication(Long id) {

		applicationRepo.delete(id);
	}

}

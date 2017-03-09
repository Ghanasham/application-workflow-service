package com.softcell.application.workflow.service.service.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softcell.application.workflow.service.service.ApplicantService;
import com.softcell.application.workflow.service.domain.Applicant;
import com.softcell.application.workflow.service.repo.ApplicantRepository;

@Service
public class DBApplicantService implements ApplicantService{

	@Autowired
	ApplicantRepository applicantRepo;
	
	@Override
	public Applicant getApplicant(Long id) {
		return applicantRepo.findOne(id);
	}

	@Override
	public Applicant saveApplicant(Applicant applicant) {
		return applicantRepo.save(applicant);
	}

	@Override
	public void deleteApplicant(Long id) {
		applicantRepo.delete(id);
		
	}

}

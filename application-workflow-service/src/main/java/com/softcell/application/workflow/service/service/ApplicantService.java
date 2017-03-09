package com.softcell.application.workflow.service.service;

import com.softcell.application.workflow.service.domain.Applicant;

public interface ApplicantService {

	public Applicant getApplicant(Long id);
	
	public Applicant saveApplicant(Applicant applicant);
	
	public void deleteApplicant(Long id);
}

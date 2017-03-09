package com.softcell.application.workflow.service.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.softcell.application.workflow.service.domain.Applicant;

@Repository
public interface ApplicantRepository extends CrudRepository<Applicant, Long> {

}

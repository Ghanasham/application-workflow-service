package com.softcell.application.workflow.service.repo;

import org.springframework.data.repository.CrudRepository;

import com.softcell.application.workflow.service.domain.Application;

public interface ApplicationRepository extends CrudRepository<Application, Long>{

}

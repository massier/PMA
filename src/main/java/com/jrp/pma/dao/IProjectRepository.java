package com.jrp.pma.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.jrp.pma.entities.Project;

//CrudRepository<class of primary id, type of primary id> primary id = projectId
//must have @autowire in controller to inject instance
public interface IProjectRepository extends CrudRepository<Project, Long> {
	
	@Override
	public List<Project> findAll();
}

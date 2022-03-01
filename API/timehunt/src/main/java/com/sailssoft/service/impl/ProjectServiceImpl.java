package com.sailssoft.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.common.base.Optional;
import com.sailssoft.dao.ProjectRepository;
import com.sailssoft.model.Project;
import com.sailssoft.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectRepository repository;
	@Override
	public ResponseEntity<String> deleteProject(long projectid) {
		if(repository.existsById(projectid)) {
			repository.deleteById(projectid);
			return new ResponseEntity<String>("project deleted",HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<String>("Project is Not Available",HttpStatus.NO_CONTENT);
		}
	}

	@Override
	public ResponseEntity<String> updateProject(Project project) {
		java.util.Optional<Project> projectp=repository.findById(project.getId());
		if(projectp.isPresent()) {
			Project pr=projectp.get();
			pr.setId(project.getId()); 
			pr.setDescription(project.getDescription());
			pr.setName(project.getName());
			pr.setStatus(project.getStatus());
			pr.setClient(project.getClient());
			pr.setStart_Date(project.getStart_Date());
			pr.setEnd_date(project.getEnd_date());
			repository.save(pr);
			return new  ResponseEntity<>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@Override
	public ResponseEntity<String> addProject(Project project) {
		Project proj=repository.save(project);
		if(proj!=null) {
			return new ResponseEntity<String>("project added",HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<String>("project ditn't add",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<List<Project>> getProject() {
		
		return new ResponseEntity<>(repository.findAll(),HttpStatus.OK);
	}

	
	
}

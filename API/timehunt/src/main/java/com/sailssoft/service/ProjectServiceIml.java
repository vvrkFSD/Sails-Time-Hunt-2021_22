package com.sailssoft.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sailssoft.dao.ProjectRepository;
import com.sailssoft.model.Project;

@Service
public class ProjectServiceIml implements ProjectService{

	private ProjectRepository projectRepository;

	
	@Override
	public ResponseEntity<String> addProject(Project project) {
		projectRepository.save(project);
		return new ResponseEntity<String>("project saved",HttpStatus.CREATED);
	}


	@Override
	public ResponseEntity<String> deleteProject(int id) {
		Optional<Project> project_id=projectRepository.findById((long)id);
		if(project_id.isPresent()) {
			projectRepository.delete(project_id.get());
			return new ResponseEntity<String>("project deleted successfully",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("project is not available",HttpStatus.NO_CONTENT);
		}
	}


	@Override
	public Optional<Project> updateProject(Project project) {
		 projectRepository.save(project);
		 return projectRepository.findById(project.getProjectId());
	}
	
	@Override
	public List<Project> allProjects(){
		return projectRepository.findAllProjects();
	}

}

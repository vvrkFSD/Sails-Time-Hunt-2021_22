package com.sailssoft.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sailssoft.model.Project;
import com.sailssoft.service.ProjectService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class ProjectController {

	@Autowired
	private ProjectService projectService;
	
	@PostMapping("/project")
	public ResponseEntity<String> addProject(@RequestBody Project project){
		return projectService.addProject(project);
	}
	
	@GetMapping("/projects")
	public ResponseEntity<List<Project>> findAllProjects(){
		return projectService.getProject();
		
	}
	
	@DeleteMapping("/project")
	public ResponseEntity<String> deleteProject(@RequestParam long projectId){
		return projectService.deleteProject(projectId);
	}

	@PutMapping("/project")
	public ResponseEntity<String> updateProject(@RequestBody Project project){ 
		return  projectService.updateProject(project);

  
	
	
	}

}

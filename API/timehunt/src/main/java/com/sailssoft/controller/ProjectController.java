package com.sailssoft.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sailssoft.model.Project;
import com.sailssoft.service.ProjectService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class ProjectController {
	
	private final ProjectService projectService;
	
	@PostMapping(path="/admin/projects")
	public ResponseEntity<?> addProject(@RequestBody Project project){
		return projectService.addProject(project);
	}
	
	@DeleteMapping("/admin/delete/project/{id}")
	public ResponseEntity<String> deleteProject(@PathVariable int id){
		return projectService.deleteProject(id);
		
	}
	
	@PutMapping("/admin/update/project")
	 public Optional<Project> updateProject(@RequestBody Project project) {
		return projectService.updateProject(project);
	 }
	

	@GetMapping("/admin/projects")
	public List<Project> getAllProjects(){
		return projectService.allProjects();
	}

}

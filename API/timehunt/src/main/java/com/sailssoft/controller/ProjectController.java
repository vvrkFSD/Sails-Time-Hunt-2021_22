package com.sailssoft.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sailssoft.model.Project;
import com.sailssoft.service.AppUserService;

import lombok.AllArgsConstructor;



@RestController
@RequestMapping("/api/v1/admin")
@AllArgsConstructor
public class ProjectController {

	private ProjectRepository projectRepository;
	private AppUserService appuserService;
	
	@PostMapping("/project")
	public ResponseEntity<String> addProject(@RequestBody Project project) {
		
		projectRepository.save(project);
		return new ResponseEntity<String>("project added",HttpStatus.CREATED);
	}
	
	
	@DeleteMapping("/project/{id}")
	public ResponseEntity<String> deleteProject(@PathVariable int id){
		Optional<Project> project_id=projectRepository.findById((long)id);
		if(project_id.isPresent()) {
			projectRepository.delete(project_id.get());
			return new ResponseEntity<String>("project deleted successfully",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("project is not available",HttpStatus.NO_CONTENT);
		}
		
	}
	
	
	@GetMapping("/projects")
	public List<Project> getAllProjects(){
		
		return projectRepository.findAll();
	}
	
	@PutMapping("/update/status/{project_id}")
	public ResponseEntity<Project> updateProject(@PathVariable long project_id,@RequestBody Project project)
	{
		Project getpro=projectRepository.getById(project_id);
		getpro.setStatus(project.getStatus());
		Project p=projectRepository.save(getpro);
		return ResponseEntity.ok().body(p);
	}
  @PostMapping(path="/admin/projects/{email}")
	public ResponseEntity<?> addProject(@RequestBody Project project,@PathVariable("email") String email){
		return appUserService.addProject(project, email);
	}
}

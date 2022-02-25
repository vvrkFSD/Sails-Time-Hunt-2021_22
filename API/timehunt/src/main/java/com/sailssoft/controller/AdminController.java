package com.sailssoft.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sailssoft.dao.AppUserRepository;
import com.sailssoft.dao.ClientRepository;
import com.sailssoft.dao.ProjectRepository;
import com.sailssoft.model.AppUser;
import com.sailssoft.model.Client;
import com.sailssoft.model.Project;
import com.sailssoft.service.AppUserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class AdminController {
	private AppUserService appUserService;

	
	private AppUserRepository userRepository;
	
	private ClientRepository clientRepository;
	
	private ProjectRepository projectRepository;


	
	
	
	@DeleteMapping("/user/delete/{id}")
	 public  ResponseEntity<String> deleteuser(@PathVariable int id){
		Optional<AppUser> userd=userRepository.findById((long) id);
		if(userd.isPresent()) {
			userRepository.delete(userd.get());
			return new ResponseEntity<String>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
	}

	
	@PostMapping("/clients")
	public ResponseEntity<String> addClient(@RequestBody Client client) {
		
		clientRepository.save(client);
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}
	
	@DeleteMapping("delete/client/{id}")
	public ResponseEntity<String> deleteClient(@PathVariable int id){
		Optional<Client> client_id=clientRepository.findById((long)id);
		if(client_id.isPresent()) {
			clientRepository.delete(client_id.get());
			return new ResponseEntity<String>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
		}
		
	}
	
	@GetMapping("/clients")
	public List<Client> findAllClients(){
		return clientRepository.findAll();
	}
	
	
	
	@PostMapping("/project")
	public ResponseEntity<String> addProject(@RequestBody Project project) {
		
		projectRepository.save(project);
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}
	
	//Deleting Project
	@DeleteMapping("delete/project/{id}")
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
	
	@PutMapping("/update/project")
	 public Optional<Project> updateProject(@RequestBody Project project) {
		
		 projectRepository.save(project);
		 return projectRepository.findById(project.getProjectId());
	 }
	
	@GetMapping("/projects")
	public List<Project> getAllProjects(){
		return appUserService.allProjects();
	}
}

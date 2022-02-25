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
@RequestMapping("/api/v1")
@AllArgsConstructor
public class ProjectController {
	
	private final AppUserService appUserService;
	
	@PostMapping(path="/admin/projects/{email}")
	public ResponseEntity<?> addProject(@RequestBody Project project,@PathVariable("email") String email){
		return appUserService.addProject(project, email);
	}

}

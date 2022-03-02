package com.sailssoft.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.sailssoft.model.Project;

public interface ProjectService {

	public ResponseEntity<String> addProject(Project project);
	public ResponseEntity<String> deleteProject(int id);
	public Optional<Project> updateProject(Project project);
	public List<Project> allProjects();
}

package com.sailssoft.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sailssoft.model.Project;


public interface ProjectService {


	ResponseEntity<String> deleteProject(long projectid);

	ResponseEntity<String> updateProject(Project project);

	ResponseEntity<String> addProject(Project project);

	ResponseEntity<List<Project>> getProject();
}

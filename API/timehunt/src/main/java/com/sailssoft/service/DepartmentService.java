package com.sailssoft.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.sailssoft.model.Department;
import com.sailssoft.model.Project;

public interface DepartmentService {
	
	ResponseEntity<String> deleteDept(String dept_name);

	ResponseEntity<String> addDept(Department dept);

	ResponseEntity<List<Department>> getDept();

	
	
}
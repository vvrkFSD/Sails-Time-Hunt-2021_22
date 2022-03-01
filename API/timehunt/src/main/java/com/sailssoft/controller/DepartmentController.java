package com.sailssoft.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sailssoft.dao.DepartmentRepository;
import com.sailssoft.dao.ProjectRepository;
import com.sailssoft.model.Department;
import com.sailssoft.model.Project;
import com.sailssoft.service.AppUserService;
import com.sailssoft.service.DepartmentService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/admin")
@AllArgsConstructor
public class DepartmentController {
	
	@Autowired
	private DepartmentService departService;
	
	@PostMapping("/department")
	public ResponseEntity<String> addDepartemnt(@RequestBody Department dept) {
		
		return departService.addDept(dept);
		}
	
	@GetMapping("/department")
	public ResponseEntity<List<Department>> getAllDepartments(){
		
		return departService.getDept();
	}
	
	@DeleteMapping("/department/{dept_name}")
	public ResponseEntity<String> deleteProject(@PathVariable String dept_name){

		return departService.deleteDept(dept_name);
		
	}

}

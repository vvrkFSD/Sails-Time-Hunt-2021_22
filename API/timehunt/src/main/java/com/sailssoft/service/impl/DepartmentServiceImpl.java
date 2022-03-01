package com.sailssoft.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sailssoft.dao.DepartmentRepository;
import com.sailssoft.model.Department;
import com.sailssoft.model.Project;
import com.sailssoft.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	
	@Autowired
	private DepartmentRepository repository;
	
	@Override
	public ResponseEntity<String> deleteDept(String dept_name) {
		
		if(repository.existsById(dept_name)) {
			repository.deleteById(dept_name);
			return new ResponseEntity<String>("Department deleted",HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<String>("Department is not Available",HttpStatus.NO_CONTENT);
		}
	}

	@Override
	public ResponseEntity<String> addDept(Department dept) {
		
		Department d=repository.save(dept);
		if(d!=null)
		{
			return new ResponseEntity<String>("Department added",HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<String>("Department didn't add",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<List<Department>> getDept() {
		
		return new ResponseEntity<>(repository.findAll(),HttpStatus.OK);
	}

}

package com.sailssoft.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sailssoft.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, String> {

}

package com.sailssoft.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sailssoft.model.Project;

public interface ProjectRepository extends JpaRepository<Project,Long> {
	
	@Query(value="select * from project",nativeQuery=true)
	List<Project> findAllProjects();

}

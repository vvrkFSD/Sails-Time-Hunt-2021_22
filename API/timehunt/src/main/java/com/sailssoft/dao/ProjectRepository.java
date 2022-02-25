package com.sailssoft.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sailssoft.model.AppUser;
import com.sailssoft.model.Project;

public interface ProjectRepository extends JpaRepository<Project,Long>{

	
}

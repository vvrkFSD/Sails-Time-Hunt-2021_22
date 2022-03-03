package com.sailssoft.dao;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.sailssoft.model.Project;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {
	
	Project findByName(String name);
}

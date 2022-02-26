package com.sailssoft.dao;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.sailssoft.model.Project;
import org.springframework.data.jpa.repository.Query;


public interface ProjectRepository extends JpaRepository<Project,Long> {
	
	@Query(value="select * from project",nativeQuery=true)
	List<Project> findAllProjects();

	Project findByName(String name);

}

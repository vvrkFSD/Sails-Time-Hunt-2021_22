package com.sailssoft.dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.sailssoft.model.AppUser;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser,Long>{

	AppUser findByEmail(String email);

	@Query(value="select email from app_user",nativeQuery=true)
	List<AppUser> findAllUsers();

}

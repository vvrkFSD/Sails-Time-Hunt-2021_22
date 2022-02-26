package com.sailssoft.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sailssoft.model.AppUser;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser,Long>{

	AppUser findByEmail(String email);
}

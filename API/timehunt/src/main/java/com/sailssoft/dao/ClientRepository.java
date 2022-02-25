package com.sailssoft.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sailssoft.model.AppUser;
//import com.sailssoft.controller.optional;
import com.sailssoft.model.Client;

public interface ClientRepository extends JpaRepository<Client,Long>{

	Optional<Client> findById(int id);
	
	

}

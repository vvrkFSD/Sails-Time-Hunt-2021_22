package com.sailssoft.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sailssoft.model.AppUser;
//import com.sailssoft.controller.optional;
import com.sailssoft.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long>{

	Client findByNameAndLocation(String name,String location);
	

	Optional<Client> findById(int id);

}

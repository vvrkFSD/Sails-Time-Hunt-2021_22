package com.sailssoft.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sailssoft.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long>{

	Client findByNameAndLocation(String name,String location);
	
}

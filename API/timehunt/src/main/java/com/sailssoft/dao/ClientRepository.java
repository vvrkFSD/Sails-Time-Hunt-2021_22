package com.sailssoft.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sailssoft.model.Client;

public interface ClientRepository extends JpaRepository<Client,Long>{

}

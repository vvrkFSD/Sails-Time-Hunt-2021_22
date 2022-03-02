package com.sailssoft.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import com.sailssoft.model.Client;



public interface ClientService {
	
	public ResponseEntity<String> addClient(Client client);
	
	public ResponseEntity<List<Client>> gettingAllClients();

	public ResponseEntity<String> deleteClient(int id);
	
	
}


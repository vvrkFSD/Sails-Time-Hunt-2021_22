package com.sailssoft.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sailssoft.dao.ClientRepository;
import com.sailssoft.model.Client;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClientService {

	private final ClientRepository clientRepository;
	
	public ResponseEntity<String> addClient(Client client){
		Client addedClient = clientRepository.findByNameAndLocation(client.getName(), client.getName());
		if(addedClient!=null)
			return new ResponseEntity<String>("client with name and location already added",HttpStatus.UNPROCESSABLE_ENTITY);
		clientRepository.save(client);
		return new ResponseEntity<String>("client added successfully",HttpStatus.CREATED);
	}
	
	public ResponseEntity<List<Client>> gettingAllClients(){
		return new ResponseEntity<List<Client>>(clientRepository.findAll(),HttpStatus.OK);
	}
	
	
}


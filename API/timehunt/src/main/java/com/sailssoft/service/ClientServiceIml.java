package com.sailssoft.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sailssoft.dao.ClientRepository;
import com.sailssoft.model.Client;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClientServiceIml implements ClientService{
private final ClientRepository clientRepository;
	
    @Override
	public ResponseEntity<String> addClient(Client client){
		Client addedClient = clientRepository.findByNameAndLocation(client.getName(), client.getName());
		if(addedClient!=null)
			return new ResponseEntity<String>("client with name and location already added",HttpStatus.UNPROCESSABLE_ENTITY);
		clientRepository.save(client);
		return new ResponseEntity<String>("client added successfully",HttpStatus.CREATED);
	}
	
    @Override
	public ResponseEntity<List<Client>> gettingAllClients(){
		return new ResponseEntity<List<Client>>(clientRepository.findAll(),HttpStatus.OK);
	}
    
    @Override
    public ResponseEntity<String> deleteClient(int id){
		Optional<Client> client_id=clientRepository.findById((long)id);
		if(client_id.isPresent()) {
			clientRepository.delete(client_id.get());
			return new ResponseEntity<String>("client deleted successfully",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("clent not found",HttpStatus.NO_CONTENT);
		}
		
	}

}

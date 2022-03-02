package com.sailssoft.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sailssoft.model.Client;
import com.sailssoft.service.ClientService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class ClientController {
	
	private ClientService clientService;
	
	@PostMapping("/admin/clients")
	public ResponseEntity<String> addClient(@RequestBody Client client) {
		
		return clientService.addClient(client);
	}
	
	@GetMapping("/admin/clients")
	public ResponseEntity<List<Client>> findAllClients(){
		return clientService.gettingAllClients();
	}
	
	@DeleteMapping("/admin/delete/client/{id}")
	public ResponseEntity<String> deleteClient(@PathVariable int id){
		return clientService.deleteClient(id);
	}

}

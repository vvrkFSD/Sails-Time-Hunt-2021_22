package com.sailssoft.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sailssoft.model.AppUser;
import com.sailssoft.model.Client;
import com.sailssoft.service.AppUserService;
import com.sailssoft.service.ClientService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class AdminController {
	private AppUserService appUserService;
	private ClientService clientService;

	@PostMapping(path="admin/users")
	public ResponseEntity<?> addUser(@RequestBody AppUser user) {
		
		return appUserService.singnUpUser(user);
	}
	
	@GetMapping(path="admin/users")
	public ResponseEntity<?> findAllUsers() {
		return appUserService.gettingAllUsers();
	}
	
  
	@PostMapping(path="admin/clients")
	public ResponseEntity<String> addClient(@RequestBody Client client){
		return clientService.addClient(client);
	}
	
	@GetMapping(path="admin/clients")
	public ResponseEntity<List<Client>> getAllClients(){
		return clientService.gettingAllClients();
	}
	
	
	
	
	
}

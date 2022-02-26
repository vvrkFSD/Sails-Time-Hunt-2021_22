package com.sailssoft.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sailssoft.dao.AppUserRepository;
import com.sailssoft.dao.ClientRepository;
import com.sailssoft.dao.ProjectRepository;
import com.sailssoft.model.AppUser;
import com.sailssoft.model.Client;
import com.sailssoft.model.Project;
import com.sailssoft.service.AppUserService;


import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/admin")
@AllArgsConstructor
public class AdminController {
	private AppUserService appUserService;
	
	private AppUserRepository userRepository;
	
	private ClientRepository clientRepository;
	

	@PostMapping(path="users")
	public String addUser(@RequestBody AppUser user) {
		
		return appUserService.singnUpUser(user);
	}
	
	@GetMapping(path="users")
	public List<AppUser> findAllUsers() {
		return appUserService.gettingAllUsers();
	}
	
	@DeleteMapping("/user/delete/{id}")
	 public  ResponseEntity<String> deleteuser(@PathVariable int id){
		Optional<AppUser> userd=userRepository.findById((long) id);
		if(userd.isPresent()) {
			userRepository.delete(userd.get());
			return new ResponseEntity<String>("user deleted successfully",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("user not available",HttpStatus.NO_CONTENT);
		}
	}
	
	@PostMapping("/clients")
	public ResponseEntity<String> addClient(@RequestBody Client client) {
		
		clientRepository.save(client);
		return new ResponseEntity<String>("client added",HttpStatus.CREATED);
	}
	
	@DeleteMapping("delete/client/{id}")
	public ResponseEntity<String> deleteClient(@PathVariable int id){
		Optional<Client> client_id=clientRepository.findById((long)id);
		if(client_id.isPresent()) {
			clientRepository.delete(client_id.get());
			return new ResponseEntity<String>("client deleted successfully",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("client not available",HttpStatus.NO_CONTENT);
		}
		
	}
	
	@GetMapping("/clients")
	public List<Client> findAllClients(){
		return clientRepository.findAll();
	}
	
	
	
	
}

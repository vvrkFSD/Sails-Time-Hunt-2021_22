package com.sailssoft.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sailssoft.dao.AppUserRepository;
import com.sailssoft.model.AppUser;
import com.sailssoft.service.AppUserService;


import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/admin")
@AllArgsConstructor
public class AdminController {
	private AppUserService appUserService;
	
	private AppUserRepository userRepository;

	@PostMapping(path="users")
	public String addUser(@RequestBody AppUser user) {
		
		return appUserService.singnUpUser(user);
	}
	
	@GetMapping(path="users")
	public List<AppUser> findAllUsers() {
		return appUserService.gettingAllUsers();
	}
	
	@DeleteMapping("/delete/{id}")
	 public  ResponseEntity<String> deleteuser(@PathVariable int id){
		Optional<AppUser> userd=userRepository.findById((long) id);
		if(userd.isPresent()) {
			userRepository.delete(userd.get());
			return new ResponseEntity<String>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
	}
	
	
}

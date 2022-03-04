package com.sailssoft.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sailssoft.dao.AppUserRepository;
import com.sailssoft.dto.RegistrationRequest;
import com.sailssoft.model.AppUser;
import com.sailssoft.service.AppUserService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class AdminController {
	private AppUserService appUserService;
	private AppUserRepository userRepository;
	

	@PostMapping(path="/admin/users")
	public ResponseEntity<?> addUser(@RequestBody RegistrationRequest user) {
		
		return appUserService.singnUpUser(user.getEmail(),user.getDepartment(),user.getFirstName());
	}
	
	@GetMapping(path="users")
	public ResponseEntity<List<String>> findAllUsers() {
		return appUserService.gettingAllUsers();
	}
	
	
	@PutMapping(path="/admin/users")
	public ResponseEntity<?> assaigningProjectToUser(@RequestParam Long projectId,@RequestParam Long userId){
		
		return appUserService.assaigningProjectToUser(projectId, userId);
	}
	
	@DeleteMapping("/admin/user/delete/{id}")
	 public  ResponseEntity<String> deleteuser(@PathVariable int id){
		Optional<AppUser> userd=userRepository.findById((long) id);
		if(userd.isPresent()) {
			userRepository.delete(userd.get());
			return new ResponseEntity<String>("user deleted successfully",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("user not found",HttpStatus.NOT_FOUND);
		}
	}

}

package com.sailssoft.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sailssoft.model.AppUser;
import com.sailssoft.service.AppUserService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/admin")
@AllArgsConstructor
public class AdminController {
	private AppUserService appUserService;

	@PostMapping(path="users")
	public String addUser(@RequestBody AppUser user) {
		
		return appUserService.singnUpUser(user);
	}
	
	@GetMapping(path="users")
	public List<AppUser> findAllUsers() {
		return appUserService.gettingAllUsers();
	}
	
	
}

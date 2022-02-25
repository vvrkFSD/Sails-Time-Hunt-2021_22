package com.sailssoft.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.sailssoft.model.UserTransaction;
import com.sailssoft.service.AppUserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class UserTransactionController {
	private final AppUserService appUserService;
	
	@PostMapping(path="timesheet/{email}")
	public ResponseEntity<String> addUserTransaction(UserTransaction userTransaction,String email){
		return appUserService.addUserTransaction(userTransaction,email);
	}
	
	@GetMapping(path="timesheet/{email}")
	public ResponseEntity<List<UserTransaction>> gettingUserTransactionsByUserId(@PathVariable("email") String email){
		return appUserService.getAllUserTransactions(email);
	}

}

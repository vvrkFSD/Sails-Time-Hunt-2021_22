package com.sailssoft.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sailssoft.dto.UserTransactiondto;
import com.sailssoft.model.UserTransaction;
import com.sailssoft.service.UserTransactionService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class UserTransactionController {
	
	@Autowired
	private final UserTransactionService userTransactionService;
	
	
	@PostMapping(path="timesheet")
	public ResponseEntity<?> addUserTransaction(@RequestBody UserTransactiondto userTransactiondto){
		return userTransactionService.addUserTransaction(userTransactiondto);
	}
	
	@GetMapping(path="timesheet/{email}")
	public ResponseEntity<List<UserTransaction>> gettingUserTransactionsByUserId(@PathVariable("email") String email){
		return userTransactionService.getAllUserTransactions(email);
	}
	
	
	@GetMapping(path="timesheet/{email}/{date}")
	public ResponseEntity<List<UserTransaction>> gettingUserTransactionsByDate(@PathVariable("email") String email ,@PathVariable("date") String date){
		return userTransactionService.getAllUserTransactionsByDate(email,date);
	}
	
	/*@DeleteMapping(path="timesheet/{email}/{date}")
	public ResponseEntity<String> deleteDayWork(@PathVariable("email") String email ,@PathVariable("date") String date){
		
		return userTransactionService.deleteDayWork(email,date);
	}
	*/
	

}

package com.sailssoft.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.sailssoft.dao.UserTransactionRepository;
import com.sailssoft.model.UserTransaction;
import com.sailssoft.service.AppUserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class UserTransactionController {
	private final AppUserService appUserService;
	
	private final UserTransactionRepository userTransactionRepository;
	
	@PostMapping(path="timesheet")
	public ResponseEntity<String> addUserTransaction(UserTransaction userTransaction,@RequestParam("email") String email,@RequestParam("projectName") String projectName){
		return appUserService.addUserTransaction(userTransaction,email,projectName);
	}
	
	@GetMapping(path="timesheet/{email}")
	public ResponseEntity<List<UserTransaction>> gettingUserTransactionsByUserId(@PathVariable("email") String email){
		return appUserService.getAllUserTransactions(email);
	}
	
	/*@DeleteMapping(path="timesheet")
	public ResponseEntity<?> deletePerticularDayWork(@RequestParam String taskDate){
		List<UserTransaction> userTransactions = userTransactionRepository.findByTaskDate(taskDate);
		
		if(userTransactions==null)return new ResponseEntity<String>("no data found",HttpStatus.NOT_FOUND);
		for(UserTransaction u:userTransactions) {
			userTransactionRepository.delete(u);
		}
		
		return new ResponseEntity<String>("deleted successfully",HttpStatus.OK);
	}
	
	@DeleteMapping(path="timesheet")
	public ResponseEntity<?> deletePerticularTask(@RequestParam String task){
		List<UserTransaction> userTransactions = userTransactionRepository.findByTaskName(task);
		
		if(userTransactions==null)return new ResponseEntity<String>("no data found",HttpStatus.NOT_FOUND);
		for(UserTransaction u:userTransactions) {
			userTransactionRepository.delete(u);
		}
		
		return new ResponseEntity<String>("deleted successfully",HttpStatus.OK);
	}
	*/

}

package com.sailssoft.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.sailssoft.dto.UserTransactiondto;
import com.sailssoft.model.UserTransaction;


public interface UserTransactionService {

	ResponseEntity<?> addUserTransaction(UserTransactiondto userTransactiondto);
	
	ResponseEntity<List<UserTransaction>> getAllUserTransactions(String email);
	
	ResponseEntity<List<UserTransaction>> getAllUserTransactionsByDate(String email, String date);
	
	ResponseEntity<String> deleteDayWork(String email, String date);
	
}

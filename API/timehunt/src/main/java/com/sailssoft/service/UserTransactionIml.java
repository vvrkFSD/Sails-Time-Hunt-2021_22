package com.sailssoft.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sailssoft.dao.AppUserRepository;
import com.sailssoft.dao.UserTransactionRepository;
import com.sailssoft.dto.UserTransactiondto;
import com.sailssoft.model.AppUser;
import com.sailssoft.model.UserTransaction;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserTransactionIml implements UserTransactionService{

	private final UserTransactionRepository userTransactionRepository;
	private final AppUserRepository appUserRepository;
	
	@Override
	public ResponseEntity<?> addUserTransaction(UserTransactiondto userTransactiondto){
		UserTransaction userTransaction = new UserTransaction();
		AppUser user = appUserRepository.findByEmail(userTransactiondto.getEmail());
		userTransaction.setUser(user);
		
		userTransaction.setDescription(userTransactiondto.getDescription());
		LocalDate dt =LocalDate.parse(userTransactiondto.getTaskDate());
		userTransaction.setTaskDate(dt);
		userTransaction.setTaskName(userTransactiondto.getTaskName());
		userTransaction.setTimeSpentInHour(userTransactiondto.getTimeSpentInHour());
		userTransaction.setTimeSpentInMinute(userTransactiondto.getTimeSpentInMinute());
		userTransactionRepository.save(userTransaction);
		return new ResponseEntity<UserTransaction>(userTransaction,HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<List<UserTransaction>> getAllUserTransactions(String email) {
		
		AppUser user =appUserRepository.findByEmail(email);
		
		List<UserTransaction> trans = userTransactionRepository.getAllUserTransactions(user.getId());
		return new ResponseEntity<List<UserTransaction>>(trans,HttpStatus.OK);
	}


	@Override
	public ResponseEntity<List<UserTransaction>> getAllUserTransactionsByDate(String email, String date) {
		LocalDate dt =LocalDate.parse(date);
		int year = dt.getYear();
		int day =dt.getDayOfMonth();
		int month =dt.getMonthValue();
		AppUser user = appUserRepository.findByEmail(email);
		return new ResponseEntity<List<UserTransaction>>(userTransactionRepository.getAllUserTransactionsByYear(year, month, day, user.getId()),HttpStatus.OK);
	}


	@Override
	public ResponseEntity<String> deleteDayWork(String email, String date) {
		LocalDate dt =LocalDate.parse(date);
		int year = dt.getYear();
		int day =dt.getDayOfMonth();
		int month =dt.getMonthValue();
		AppUser user = appUserRepository.findByEmail(email);
	    userTransactionRepository.deleteByDateAndEmail(year, month, day, user.getId());
		return new ResponseEntity<String>("deleted successfully",HttpStatus.OK);
	}


}

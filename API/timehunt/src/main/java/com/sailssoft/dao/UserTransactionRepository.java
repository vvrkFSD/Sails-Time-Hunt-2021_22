package com.sailssoft.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sailssoft.model.UserTransaction;

@Repository
public interface UserTransactionRepository extends JpaRepository<UserTransaction,Long>{

	@Query(value="select *  from user_transaction where user_transaction.user_id=?1",nativeQuery=true)
	List<UserTransaction> getAllUserTransactions(Long id);
	
	
	List<UserTransaction> findByTaskDate(String taskDate);
	
	List<UserTransaction> findByTaskName(String taskName);
	
}

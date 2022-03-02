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
	
	@Query(value="select * from user_transaction where year(user_transaction.task_date)=?1 and month(user_transaction.task_date)=?2 and day(user_transaction.task_date)=?3 and user_transaction.user_id=?4",nativeQuery=true)
	List<UserTransaction> getAllUserTransactionsByYear(int year,int month,int day,long id);
	
	@Query(value="delete from user_transaction where year(user_transaction.task_date)=?1 and month(user_transaction.task_date)=?2 and day(user_transaction.task_date)=?3 and user_transaction.user_id=?4",nativeQuery=true)
	void deleteByDateAndEmail(int year,int month,int day,long id);
}

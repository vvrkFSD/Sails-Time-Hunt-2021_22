package com.sailssoft.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sailssoft.model.UserTransaction;

@Repository
public interface UserTransactionRepository extends JpaRepository<UserTransaction,Long>{

	
}

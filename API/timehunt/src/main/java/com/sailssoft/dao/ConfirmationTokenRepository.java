package com.sailssoft.dao;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sailssoft.model.ConfirmationToken;

@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken,Long>{

	ConfirmationToken findByToken(String token);

	 @Transactional
	    @Modifying
	    @Query("UPDATE ConfirmationToken c " +
	            "SET c.confirmedAt = ?2 " +
	            "WHERE c.token = ?1")
	    int updateConfirmedAt(String token,
	                          LocalDateTime confirmedAt);
}

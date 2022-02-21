package com.sailssoft.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.sailssoft.dao.ConfirmationTokenRepository;
import com.sailssoft.model.ConfirmationToken;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {

	private final ConfirmationTokenRepository confirmationTokenRepository;
	
	public void saveConfirmationToken(ConfirmationToken token) {
		confirmationTokenRepository.save(token);
	}
	
	public ConfirmationToken getToken(String token) {
        return confirmationTokenRepository.findByToken(token);
    }

    public int setConfirmedAt(String token) {
        return confirmationTokenRepository.updateConfirmedAt(
                token, LocalDateTime.now());
    }

}

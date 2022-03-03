package com.sailssoft.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.sailssoft.dto.ChangePassword;
import com.sailssoft.dto.RegistrationRequest;
import com.sailssoft.dto.ResetPasswordRequest;
import com.sailssoft.model.AppUser;

public interface AppUserService {

	public ResponseEntity<?> singnUpUser(String email,String dept_name);

	public ResponseEntity<String> changePassword(String token, ResetPasswordRequest resetPassword);

	public ResponseEntity<String> changePasswordWithOldPassword(ChangePassword changePassword);

	public ResponseEntity<String> confirmToken(String token);

	public ResponseEntity<String> register(RegistrationRequest request);

	public ResponseEntity<String> updateProfile(AppUser appUser);

	public ResponseEntity<List<String>> gettingAllUsers();

	public ResponseEntity<?> assaigningProjectToUser(Long projectId, Long userId);

}

package com.sailssoft.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sailssoft.dao.AppUserRepository;
import com.sailssoft.dao.ProjectRepository;
import com.sailssoft.dto.EmailSender;
import com.sailssoft.dto.EmailValidator;
import com.sailssoft.dto.RegistrationRequest;
import com.sailssoft.dto.ResetPasswordRequest;
import com.sailssoft.model.CustomUserDetails;
import com.sailssoft.model.Project;
import com.sailssoft.model.AppUser;
import com.sailssoft.model.AppUserRole;
import com.sailssoft.model.ChangePassword;
import com.sailssoft.model.ConfirmationToken;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService{

	private final static String USER_NOT_FOUND_MSG =
			"user with email %s not found";

	private final AppUserRepository appUserRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final ConfirmationTokenService confirmationTokenService;
	private final EmailValidator emailValidator;
	private final EmailSender emailSender;
	
	private ProjectRepository projectRepository;

	

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		AppUser appuser =  appUserRepository.findByEmail(email);
		if(appuser==null) {
			throw new UsernameNotFoundException(USER_NOT_FOUND_MSG);
		}

		return new CustomUserDetails(appuser);
	}

	public String singnUpUser(AppUser user) {
		AppUser userExists = appUserRepository.findByEmail(user.getEmail());
		if(userExists!=null) {
			throw new IllegalStateException("email already taken");
		}

		boolean isValidEmail = emailValidator.test(user.getEmail());

		if(!isValidEmail) {
			throw new IllegalStateException("Email not valid");
		}

		emailSender.send(user.getEmail(),"use P@ssword!");

		String encodedPassword = bCryptPasswordEncoder
				.encode("P@ssword!");

		user.setPassword(encodedPassword);
		user.setAppUserRole(AppUserRole.USER);

	

		appUserRepository.save(user);

		return "user saved";
	}


	public boolean changePassword(String token, ResetPasswordRequest resetPassword) {
		ConfirmationToken confirmationToken = confirmationTokenService.getToken(token);
		if(!resetPassword.getPassword().equals(resetPassword.getConfirmPassword())) {
			throw new IllegalStateException("password doesnt match");
		}
		AppUser appUser = confirmationToken.getAppUser();
		appUser.setPassword(bCryptPasswordEncoder
				.encode(resetPassword.getPassword()));
		appUserRepository.save(appUser);
		return true;
	}

	public boolean changePasswordWithOldPassword(ChangePassword changePassword) {
		AppUser appUser = appUserRepository.findByEmail(changePassword.getEmail());

		if(appUser==null) {
			throw new IllegalStateException("User not found");
		}

		if(!bCryptPasswordEncoder.matches(changePassword.getOldPassword(), appUser.getPassword())) {
			throw new IllegalStateException("password does not match");
		}


		appUser.setPassword(bCryptPasswordEncoder.encode(changePassword.getNewPassword()));
		appUserRepository.save(appUser);
		return true;
	}

	@Transactional
	public boolean confirmToken(String token) {
		ConfirmationToken confirmationToken = confirmationTokenService.getToken(token);
		if(confirmationToken==null) {
			throw new IllegalStateException("token not found");
		}

		if (confirmationToken.getConfirmedAt() != null) {
			throw new IllegalStateException("email already confirmed");
		}

		LocalDateTime expiredAt = confirmationToken.getExpiredAt();

		if (expiredAt.isBefore(LocalDateTime.now())) {
			throw new IllegalStateException("token expired");
		}

		confirmationTokenService.setConfirmedAt(token);
		return true;
	}

	public String register(RegistrationRequest request) {
		boolean isValidEmail = emailValidator.test(request.getEmail());

		if(!isValidEmail) {
			throw new IllegalStateException("Email not valid");
		}

		AppUser appUser = appUserRepository.findByEmail(request.getEmail());

		if(appUser==null) {
			throw new IllegalStateException("User not found");
		}

		String token = UUID.randomUUID().toString();

		ConfirmationToken confirmationToken = new ConfirmationToken(
				token,
				LocalDateTime.now(),
				LocalDateTime.now().plusMinutes(15),
				appUser
				);

		confirmationTokenService.saveConfirmationToken(
				confirmationToken);
		String link = "http://localhost:8080/api/v1/forgot_password/reset?token="+token;
		emailSender.send(request.getEmail(),link);
		return token;
	}

	public String updateProfile(AppUser appUser) {
		AppUser user = appUserRepository.findByEmail(appUser.getEmail());
		if(appUser.getDOB()!=null) {
			user.setDOB(appUser.getDOB());
		}
		
		if(appUser.getGender()!=null) {
			user.setGender(appUser.getGender());
		}
		appUserRepository.save(user);
		return "user profile updated";
	}

	public List<AppUser> gettingAllUsers() {
		return appUserRepository.findAllUsers();
	}


}

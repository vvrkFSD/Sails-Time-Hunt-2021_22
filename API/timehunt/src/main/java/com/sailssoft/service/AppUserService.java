package com.sailssoft.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpSession;

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
import com.sailssoft.dao.UserTransactionRepository;
import com.sailssoft.dto.AppUserRole;
import com.sailssoft.dto.ChangePassword;
import com.sailssoft.dto.CustomUserDetails;
import com.sailssoft.dto.EmailSender;
import com.sailssoft.dto.EmailValidator;
import com.sailssoft.dto.RegistrationRequest;
import com.sailssoft.dto.ResetPasswordRequest;

import com.sailssoft.model.Project;
import com.sailssoft.model.AppUser;
import com.sailssoft.model.ConfirmationToken;
import com.sailssoft.model.Project;
import com.sailssoft.model.UserTransaction;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService{

	private final static String USER_NOT_FOUND_MSG =
			"user with email %s not found";

	private final AppUserRepository appUserRepository;
	private final UserTransactionRepository userTransactionRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final ConfirmationTokenService confirmationTokenService;
	private final EmailValidator emailValidator;
	private final EmailSender emailSender;

	private final ProjectRepository projectRepository;


	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		AppUser appuser =  appUserRepository.findByEmail(email);
		if(appuser==null) {
			throw new UsernameNotFoundException(USER_NOT_FOUND_MSG);
		}
		return new CustomUserDetails(appuser);
	}


	public ResponseEntity<?> login(AppUser user,HttpSession session){
		AppUser isValid = appUserRepository.findByEmail(user.getEmail());
		if(isValid==null)
			return new ResponseEntity<String>("user not found",HttpStatus.NOT_FOUND);

		if(!bCryptPasswordEncoder.matches(user.getPassword(), isValid.getPassword())) {
			return new ResponseEntity<String>("password does not match",HttpStatus.UNAUTHORIZED);
		}

		session.setAttribute("email", user.getEmail());
		return new ResponseEntity<AppUser>(user,HttpStatus.OK);
	}


	public ResponseEntity<?> login2(AppUser user, HttpSession session) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ResponseEntity<String> logout(HttpSession session){
		if(session.getAttribute("email")==null)
			return new ResponseEntity<String>("please login first",HttpStatus.NOT_FOUND);
		session.removeAttribute("email");
		session.invalidate();
		return new ResponseEntity<String>("successfully logged out",HttpStatus.OK);
	}

	public ResponseEntity<?> singnUpUser(String email) {
		AppUser userExists = appUserRepository.findByEmail(email);
		if(userExists!=null&&userExists.getAppUserRole().name().equals("USER")) {
			return new ResponseEntity<String>("email already taken",HttpStatus.SEE_OTHER);
		}

		AppUser user = new AppUser();
		boolean isValidEmail = emailValidator.test(user.getEmail());

		if(!isValidEmail) {
			return new ResponseEntity<String>("Email not valid",HttpStatus.UNPROCESSABLE_ENTITY);
		}

		emailSender.send(email,"use P@ssword!");

		String encodedPassword = bCryptPasswordEncoder
				.encode("P@ssword!");

		user.setPassword(encodedPassword);
		user.setAppUserRole(AppUserRole.USER);

		appUserRepository.save(user);

		return new ResponseEntity<AppUser>(user,HttpStatus.CREATED);
	}


	public ResponseEntity<String> changePassword(String token, ResetPasswordRequest resetPassword) {
		ConfirmationToken confirmationToken = confirmationTokenService.getToken(token);
		if(!resetPassword.getPassword().equals(resetPassword.getConfirmPassword())) {
			return new ResponseEntity<String>("password doesnt match",HttpStatus.UNAUTHORIZED);
		}
		AppUser appUser = confirmationToken.getAppUser();
		appUser.setPassword(bCryptPasswordEncoder
				.encode(resetPassword.getPassword()));
		appUserRepository.save(appUser);
		return new ResponseEntity<String>("password changed successfully",HttpStatus.OK);
	}


	public ResponseEntity<String> changePasswordWithOldPassword(ChangePassword changePassword) {
		AppUser appUser = appUserRepository.findByEmail(changePassword.getEmail());

		if(appUser==null) {
			return new ResponseEntity<String>("User not found",HttpStatus.NOT_FOUND);
		}

		if(!bCryptPasswordEncoder.matches(changePassword.getOldPassword(), appUser.getPassword())) {
			return new ResponseEntity<String>("password does not match",HttpStatus.UNAUTHORIZED);
		}


		appUser.setPassword(bCryptPasswordEncoder.encode(changePassword.getNewPassword()));
		appUserRepository.save(appUser);
		return new ResponseEntity<String>("password changed successfully",HttpStatus.OK);
	}

	@Transactional
	public ResponseEntity<String> confirmToken(String token) {
		ConfirmationToken confirmationToken = confirmationTokenService.getToken(token);
		if(confirmationToken==null) {
			return new ResponseEntity<String>("token not found",HttpStatus.NOT_FOUND);
		}

		if (confirmationToken.getConfirmedAt() != null) {
			return new ResponseEntity<String>("email already confirmed",HttpStatus.UNPROCESSABLE_ENTITY);
		}

		LocalDateTime expiredAt = confirmationToken.getExpiredAt();

		if (expiredAt.isBefore(LocalDateTime.now())) {
			return new ResponseEntity<String>("token expired",HttpStatus.UNAUTHORIZED);
		}

		confirmationTokenService.setConfirmedAt(token);
		return new ResponseEntity<String>("token confirmed",HttpStatus.ACCEPTED);
	}

	public ResponseEntity<String> register(RegistrationRequest request) {
		boolean isValidEmail = emailValidator.test(request.getEmail());

		if(!isValidEmail) {
			return new ResponseEntity<String>("Email not valid",HttpStatus.UNPROCESSABLE_ENTITY);
		}

		AppUser appUser = appUserRepository.findByEmail(request.getEmail());

		if(appUser==null) {
			return new ResponseEntity<String>("User not found",HttpStatus.NOT_FOUND);
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
		return new ResponseEntity<String>("Email Sent",HttpStatus.OK);
	}

	public ResponseEntity<String> updateProfile(AppUser appUser) {
		AppUser user = appUserRepository.findByEmail(appUser.getEmail());
		if(appUser.getDOB()!=null) {
			user.setDOB(appUser.getDOB());
		}

		if(appUser.getGender()!=null) {
			user.setGender(appUser.getGender());
		}
		if(appUser.getFirstName()!=null) {
			user.setFirstName(appUser.getFirstName());
		}
		if(appUser.getLastName()!=null) {
			user.setLastName(appUser.getLastName());
		}
		appUserRepository.save(user);
		return new ResponseEntity<String>("user profile updated",HttpStatus.OK);
	}

	public ResponseEntity<List<String>> gettingAllUsers() {
		List<AppUser> allUsers= appUserRepository.findAll();
		List<String> users = new ArrayList<>();

		for(int i=0;i<allUsers.size();i++) {
			users.add(allUsers.get(i).email);
		}

		return new ResponseEntity<List<String>>(users,HttpStatus.OK);
	}
	
	
	public ResponseEntity<?> addProject(Project project,String email){
		
		projectRepository.save(project);
		return new ResponseEntity<Project>(project,HttpStatus.CREATED);
	}

	public List<Project> allProjects(){
		return projectRepository.findAllProjects();
	}
	
	public ResponseEntity<?>  assaigningProjectToUser(Long projectId,Long userId){
		Optional<AppUser> user = appUserRepository.findById(userId);
		Optional<Project> project = projectRepository.findById(projectId);
		if(user.isPresent()&&project.isPresent()) {
			user.get().setProject(project.get());
			appUserRepository.save(user.get());
			return new ResponseEntity<AppUser>(user.get(),HttpStatus.OK);
		}
		
		return new ResponseEntity<String>("not found",HttpStatus.NOT_FOUND);
		
	}
	
	public ResponseEntity<String> addUserTransaction(UserTransaction userTransaction,String email,String projectName){
		AppUser user = appUserRepository.findByEmail(email);
		userTransaction.setUser(user);
		
		userTransactionRepository.save(userTransaction);
		return new ResponseEntity<String>("added successfully",HttpStatus.CREATED);
	}


	public ResponseEntity<List<UserTransaction>> getAllUserTransactions(String email) {
		
		AppUser user =appUserRepository.findByEmail(email);
		
		List<UserTransaction> trans = userTransactionRepository.getAllUserTransactions(user.getId());
		return new ResponseEntity<List<UserTransaction>>(trans,HttpStatus.OK);
	}

}

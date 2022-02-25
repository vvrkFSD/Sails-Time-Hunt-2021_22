package com.sailssoft.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.sailssoft.dto.ChangePassword;
import com.sailssoft.dto.RegistrationRequest;
import com.sailssoft.dto.ResetPasswordRequest;
import com.sailssoft.model.AppUser;
import com.sailssoft.model.UserTransaction;
import com.sailssoft.service.AppUserService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
@SessionAttributes("setUser")
public class UserController {
	
	private final AppUserService appUserService;
	
	@PostMapping(path="login")
	public ResponseEntity<?> login(@RequestBody AppUser user,HttpSession session){
		
		return appUserService.login(user, session);
	} 
	
	@PostMapping(path="login2")
	public ResponseEntity<?> login2(@RequestBody AppUser user,HttpSession session){
		
		return appUserService.login2(user, session);
	} 
	
	
	
	@GetMapping(path="logout")
	public ResponseEntity<String> logout(HttpSession session){
		return appUserService.logout(session);
	}

	@PostMapping(path="forgot_password")
    public ResponseEntity<String> sendingEmailToGetTokenForChangingPassword(@RequestBody RegistrationRequest request) {
        return appUserService.register(request);
    }

    @GetMapping(path = "forgot_password/reset")
    public ResponseEntity<String> sendingTokenToChangePassword(@RequestParam("token") String token) {
        return appUserService.confirmToken(token);
        
    }
    
    @PostMapping(path="forgot_password/reset")
    public ResponseEntity<String> changePassword(@RequestParam("token") String token,@RequestBody ResetPasswordRequest resetPassword) {
        return appUserService.changePassword(token,resetPassword);
    }
    
    @PutMapping(path="profile")
    public ResponseEntity<String> updateProfile(@RequestBody AppUser user) {
    	return appUserService.updateProfile(user);
    }

    @PostMapping("/profile/changePassword")
	public ResponseEntity<String> changePasswordWithOldPassword(@RequestBody ChangePassword changePassword) {
		return appUserService.changePasswordWithOldPassword(changePassword);	
	}
    
   
	@GetMapping(path="admin")
	public ResponseEntity<String> adminPage() {
	
		return new ResponseEntity<String>("welcome to admin home page",HttpStatus.OK);
	}
	
	@GetMapping(path="home")
	public ResponseEntity<String> userHomePage() {
		return new ResponseEntity<String>("user home page",HttpStatus.OK);
	}
	
	
	
}

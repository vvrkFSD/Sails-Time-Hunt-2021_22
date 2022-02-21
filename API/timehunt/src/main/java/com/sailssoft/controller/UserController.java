package com.sailssoft.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sailssoft.dao.AppUserRepository;
import com.sailssoft.dto.RegistrationRequest;
import com.sailssoft.dto.ResetPasswordRequest;
import com.sailssoft.model.AppUser;
import com.sailssoft.model.ChangePassword;
import com.sailssoft.service.AppUserService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor

public class UserController {
	
	private final AppUserService appUserService;

	@GetMapping(path="forgot_password")
	public String displayForgotPasswordPage() {
		return "forgot password page";
		 //TODO : sending forgotPage link
	}
	
	@PostMapping(path="forgot_password")
    public String sendingEmailToGetTokenForChangingPassword(@RequestBody RegistrationRequest request) {
        return appUserService.register(request);
    }

    @GetMapping(path = "forgot_password/reset")
    public String sendingTokenToChangePassword(@RequestParam("token") String token) {
        if(appUserService.confirmToken(token)) {
        	return "forgot_page_toChangePassword";
        }
        //TODO :change password page for forgot_password
       return "something gone wrong";
    }
    
    @PostMapping(path="forgot_password/reset")
    public String changePassword(@RequestParam("token") String token,@RequestBody ResetPasswordRequest resetPassword) {
        if(appUserService.changePassword(token,resetPassword)) {
        	return "changed successfully";
        }
        //TODO :change password page for forgot_password
       return "something gone wrong";
    }
    
    @PutMapping(path="profile")
    public String updateProfile(@RequestBody AppUser user) {
    	return appUserService.updateProfile(user);
    }

    @PostMapping("/profile/changePassword")
	public String changePasswordWithOldPassword(@RequestBody ChangePassword changePassword) {
	
		boolean valid = appUserService.changePasswordWithOldPassword(changePassword);
		if(!valid)return "somthing went wrong";
		return "successfully changed";
	}
    
    @GetMapping(path="login")
	public String gettingLoginPage() {
		//TODO: return login page
		return "loginpage";
	}
	
	@GetMapping(path="admin")
	public String adminPage() {
		//TODO:return admin home page
		return "admin home page";
	}
	
	@GetMapping(path="home")
	public String userHomePage() {
		return "user home page";
	}
	
	
	
}

package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.test.service.UserService;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/loginform")
	public String getLoginForm() {
		return "user/loginform";
	}
	
	@RequestMapping(value = "/login")
	public String loginProc(@RequestParam(value = "email", defaultValue = "", required = true) String email,
							@RequestParam(value = "password", defaultValue = "", required = true) String password,
							Model model) {
		
		//validation
		
		//success and fail
		boolean loginFlag = userService.checkLoginFlag(email, password);
		
		if(loginFlag == false) {
			return "user/joinform";
		}
		
		//store session and redirect to main
		return "user/login-success";
	}
	
	@RequestMapping(value = "/joinform")
	public String getJoinForm() {
		return "user/joinform";
	}
	
}

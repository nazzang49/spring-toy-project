package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.dto.UserDto;
import com.test.security.AuthUser;
import com.test.security.SecurityUser;
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
		
		if(!loginFlag) {
			return "user/joinform";
		}
		
		//store session and redirect to main
		return "user/login-success";
	}
	
	@RequestMapping(value = "/joinform")
	public String getJoinForm() {
		return "user/joinform";
	}
	
	/**
	 * 
	 * @param securityUser 
	 * @param model
	 * @return mypage.jsp
	 */
	@RequestMapping(value = "/mypage")
	public String getMypage(@AuthUser SecurityUser securityUser, Model model) {
		System.out.println(securityUser.getUsername()+" is loginNow");
		
		return "user/mypage";
	}
	
	// by ajax
	@RequestMapping(value = "/checkDuplication")
	@ResponseBody
	public boolean checkDuplication(@RequestParam(value="user_email") String email) {
		// check user duplication
		return userService.checkJoinFlag(email);
	}
	
	// actual process of join
	@RequestMapping(value="/join")
	public String joinProc(@ModelAttribute("userDto") UserDto userDto, Model model) {
		boolean flag = userService.checkJoinFlag(userDto);
		
		// separate following process by flag value
		if(!flag) {
			// a reason why this return uses redirect -> need to include model attribute
			model.addAttribute("joinResult", flag);
			return "redirect:/user/joinform";
		}
		
		return "user/join-success";
	}
	
}

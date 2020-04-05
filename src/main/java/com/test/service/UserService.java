package com.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.dto.TeammateDto;
import com.test.dto.UserDto;
import com.test.repository.UserDao;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	public boolean checkLoginFlag(String email, String password) {
		TeammateDto teammateDto = userDao.getUser(email, password);
		if(teammateDto != null) {
			return true;
		}
		return false;
	}
	
	// check email duplication by ajax
	public boolean checkJoinFlag(String email) {
		UserDto userDto = userDao.getUserInfo(email);
		if(userDto != null) {
			return true;
		}
		return false;
	}
	
}

package com.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.dto.TeammateDto;
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
	
}

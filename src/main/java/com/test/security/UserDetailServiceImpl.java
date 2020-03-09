package com.test.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import com.test.repository.UserDao;

@Component
public class UserDetailServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserDao userDao;
	
	// get user info by username
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		
		return null;
	}
}

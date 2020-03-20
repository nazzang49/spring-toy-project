package com.test.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.test.dto.UserDto;
import com.test.repository.UserDao;

@Component
public class UserDetailServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserDao userDao;
	
	// get user info by username
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDto userDto = userDao.getSecurityUserByEmail(username);
		SecurityUser securityUser = new SecurityUser();
		
		// if there is a user info -> put it into security user
		if(userDto != null) {
			securityUser.setPassword(userDto.getUser_password());
			securityUser.setUsername(userDto.getUser_email());
			// set authorization level by user_role value -> might be various not just one
			securityUser.setAuthorities(Arrays.asList(new SimpleGrantedAuthority(userDto.getUser_role())));
		}
		return securityUser;
	}
}

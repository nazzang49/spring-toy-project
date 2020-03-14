package com.test.security;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

public class CustomAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {

	// logging
	private static final Logger logger = Logger.getLogger("com.test");
	
	public CustomAuthenticationEntryPoint(String loginformUrl) {
		super(loginformUrl);
	}
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		try {
			super.commence(request, response, authException);	
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
	}
}

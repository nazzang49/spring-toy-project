package com.test.security;

import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;

public class CustomAccessDeniedHandler extends AccessDeniedHandlerImpl {

	// logging
	private static final Logger logger = Logger.getLogger("com.test");
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) {
		try {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, accessDeniedException.getMessage());
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
	}
}

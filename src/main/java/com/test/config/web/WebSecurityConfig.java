package com.test.config.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import com.test.security.CustomAccessDeniedHandler;
import com.test.security.CustomAuthenticationEntryPoint;
import com.test.security.CustomAuthenticationFailureHandler;
import com.test.security.CustomAuthenticationSuccessHandler;
import com.test.security.CustomLogoutSuccessHandler;
import com.test.security.CustomPasswordEncoder;
import com.test.security.JwtAuthenticationFilter;
import com.test.security.JwtAuthorizationFilter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private AuthenticationProvider authenticationProvider;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	/**
	 * authentication method
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider);
	}
	
	/**
	 * ignoring these URLs
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
		.antMatchers("/resources/**")
		.antMatchers("/css/**")
		.antMatchers("/vendor/**")
		.antMatchers("/js/**")
		.antMatchers("/favicon/**")
		.antMatchers("/img/**")
		.antMatchers("/assets/**");
		
	}
	
	/**
	 * security rules
	 * antMatchers = intercepting request
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
        .antMatchers("/user/login").permitAll()
        .antMatchers("/user/logout").permitAll()
        .antMatchers("/chatbot/**").permitAll()
        .anyRequest().authenticated()
        .and().logout()
        .logoutUrl("/user/logout")
        .logoutSuccessHandler(logoutSuccessHandler())
        // not using csrf token
        .and().csrf().disable()
        .addFilter(jwtAuthenticationFilter())
        .addFilter(jwtAuthorizationFilter())
        .exceptionHandling()
        .accessDeniedHandler(accessDeniedHandler())
        .authenticationEntryPoint(authenticationEntryPoint());
	}
	
	/*
     * SuccessHandler bean register
     */
    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        CustomAuthenticationSuccessHandler successHandler = new CustomAuthenticationSuccessHandler();
        successHandler.setDefaultTargetUrl("/index");
        return successHandler;
    }
    
    /*
     * FailureHandler bean register
     */
    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        CustomAuthenticationFailureHandler failureHandler = new CustomAuthenticationFailureHandler();
        failureHandler.setDefaultFailureUrl("/user/loginform");
        return failureHandler;
    }
    
    /*
     * LogoutSuccessHandler bean register
     */
    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        CustomLogoutSuccessHandler logoutSuccessHandler = new CustomLogoutSuccessHandler();
        logoutSuccessHandler.setDefaultTargetUrl("/index");
        return logoutSuccessHandler;
    }
    
    /*
     * AccessDeniedHandler bean register
     */
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        CustomAccessDeniedHandler accessDeniedHandler = new CustomAccessDeniedHandler();
        accessDeniedHandler.setErrorPage("/WEB-INF/views/error/exception.jsp");
        return accessDeniedHandler;
    }
    
    /*
     * AuthenticationEntryPoint bean register
     */
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new CustomAuthenticationEntryPoint("/user/loginform");
    }
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		//use sha512 instead of bcrypt
		authProvider.setPasswordEncoder(customPasswordEncoder());
		return authProvider;
	}
	
	/*
     * login process -> caught in this filter
     */
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager());
        // request this url -> filter activated
        jwtAuthenticationFilter.setFilterProcessesUrl("/user/login");
        jwtAuthenticationFilter.setUsernameParameter("user_email");
        jwtAuthenticationFilter.setPasswordParameter("user_password");
        // session creation
        jwtAuthenticationFilter.setAllowSessionCreation(true);
        jwtAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler());
        jwtAuthenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler());
        jwtAuthenticationFilter.afterPropertiesSet();
        return jwtAuthenticationFilter;
    }
    
    /*
     * Filter bean register
     */
    @Bean
    public JwtAuthorizationFilter jwtAuthorizationFilter() throws Exception {
        JwtAuthorizationFilter jwtAuthorizationFilter = new JwtAuthorizationFilter(authenticationManager());
        return jwtAuthorizationFilter;
    }
    
    /**
     * customized password encoder (SHA 512)
     * @return CustomPasswordEncoder object
     */
    @Bean
	public CustomPasswordEncoder customPasswordEncoder() {
		return new CustomPasswordEncoder();
	}
}

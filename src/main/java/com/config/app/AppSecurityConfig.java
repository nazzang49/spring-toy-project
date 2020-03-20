package com.config.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import com.test.security.CustomPasswordEncoder;
import com.test.security.CustomUrlAuthenticationSuccessHandler;

// spring security customization
@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;
	
	/**
	 * fill out three basic configure methods (default / can be customized)
	 */
	
	@Override
	public void configure(WebSecurity web) throws Exception {		
		// ignoring -> not on the ACL (Access Control List) -> permitall
		// regex -> A (All), Z (close)
		web.ignoring().regexMatchers("\\A/assets/.*\\Z");
		web.ignoring().regexMatchers("\\A/favicon.ico\\Z");
	}
	
	// interceptor -> if requested, this process must be done
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// pass if authenticated
		http.authorizeRequests()
		.antMatchers("/user/update", "/user/logout", "/user/info").authenticated()
		
		// pass if authorized
		// 1) access("hasRole('ROLE_ADMIN')")
		// 2) hasAuthority("ADMIN")
		.antMatchers("/admin/**").hasRole("ADMIN")
		
		// pass except for uppper paths
		.anyRequest().permitAll();
		
		// csrf invalid -> usually for test
		http.csrf().disable();
		
		// login process
		http.formLogin()
		.loginPage("/user/loginform")
		.loginProcessingUrl("/user/auth")
		.failureUrl("/user/loginform?result=fail")
		.successHandler(authenticationSuccessHandler())
		// loginform parameter
		.usernameParameter("user_email")
		.passwordParameter("user_password");
		
		// logout process
		http.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
		// after logout -> go to main page
		.logoutSuccessUrl("/index")
		// reset session (=invalid)
		.deleteCookies("JSESSIONID")
		.invalidateHttpSession(true)
		.and()
		
		// exception handler
		.exceptionHandling();
		
		// remember-me -> continue login state
		http.rememberMe()
		.key("spring-toy-project")
		// checkbox -> use this function or not
		.rememberMeParameter("remember-me");
	}
	
	// related with authentication process
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).and().authenticationProvider(authenticationProvider());
	}
	
//	@Bean
//	public SecurityContextPersistenceFilter securityContextPersistenceFilter() {
//		return new SecurityContextPersistenceFilter(new HttpSessionSecurityContextRepository());
//	}
	
	@Bean
	public AuthenticationSuccessHandler authenticationSuccessHandler() {
		return new CustomUrlAuthenticationSuccessHandler();
	}
	
	// not working right now
//	@Bean
//	public RememberMeAuthenticationFilter rememberMeAuthenticationFilter() {
//		TokenBasedRememberMeServices rememberMeServices = new TokenBasedRememberMeServices("spring-toy-project", userDetailsService);
//		rememberMeServices.setParameter("remember-me");
//		RememberMeAuthenticationFilter rememberMeAuthenticationFilter = 
//				new RememberMeAuthenticationFilter(authenticationManager(), rememberMeServices);
//		
//		return rememberMeAuthenticationFilter;
//	}
	
	/**
	 * Authentication
	 * 1) provider
	 * 2) manager
	 */
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(customPassworEncoder());
		return authProvider;
	}
	
//	@Bean
//	public AuthenticationManager authenticationManager() {
//		AuthenticationManager authenticationManager = new ProviderManager(Arrays.asList(authenticationProvider()));
//		return authenticationManager;
//	}
	
	// password comparison -> input-password and password from db
	@Bean
	public CustomPasswordEncoder customPassworEncoder() {
		return new CustomPasswordEncoder();
	}
}
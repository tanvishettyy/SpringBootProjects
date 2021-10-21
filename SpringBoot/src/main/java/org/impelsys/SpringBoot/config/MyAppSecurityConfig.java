package org.impelsys.SpringBoot.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

//@Configuration
//@EnableWebSecurity // enables security management for the application //marker interface
public class MyAppSecurityConfig extends WebSecurityConfigurerAdapter { // it has some default functions

	@Autowired
	private UserDetailsService userDetailsService;

	
	 @Bean 
	 public AuthenticationProvider authProvider() {
		 
	 
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	  //db connection
		authProvider.setUserDetailsService(userDetailsService);
	  //setting the password encoder
	 //authProvider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
		//NoOpPasswordEncoder.getInstance()->no password encryption
	  authProvider.setPasswordEncoder(new BCryptPasswordEncoder()); //password encryption
	  return authProvider; }
	 

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Http basic autentication

//		http.authorizeRequests().antMatchers(HttpMethod.GET, "/Messenger").permitAll()
//		.anyRequest().authenticated().and().httpBasic().and().formLogin().disable().csrf().disable();
		
		http.authorizeRequests()
		.antMatchers(HttpMethod.GET, "/home").hasRole("USER")
		.antMatchers(HttpMethod.POST,"/users/user").hasRole("ADMIN")
		.antMatchers(HttpMethod.DELETE,"/users/remove/*").hasRole("ADMIN")
		.antMatchers(HttpMethod.GET,"/comments/*").hasRole("ADMIN")
		.anyRequest().authenticated().and().httpBasic().and().formLogin().disable().csrf().disable();
		
		// .httpBasic(); //any url has to be authenticated
		/*
		 * http.httpBasic() .and() .authorizeRequests() .antMatchers(HttpMethod.POST,
		 * "/users/user").hasRole("ADMIN") .antMatchers(HttpMethod.DELETE,
		 * "/users/delete").hasRole("ADMIN") .antMatchers(HttpMethod.GET,
		 * "/home").hasRole("USER");
		 */

	}

	@Bean
	@Override
	protected UserDetailsService userDetailsService() {

		List<UserDetails> users = new ArrayList<>();
		users.add(User.withDefaultPasswordEncoder().username("Tanvi").password("123").roles("USER").build());
		users.add(User.withDefaultPasswordEncoder().username("Root").password("123").roles("USER", "ADMIN").build());
		return new InMemoryUserDetailsManager(users);

	}

}

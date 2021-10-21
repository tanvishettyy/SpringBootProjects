package org.impelsys.SpringBoot.service;

import org.impelsys.SpringBoot.dao.UserLoginRepository;
import org.impelsys.SpringBoot.model.UserLogin;
import org.impelsys.SpringBoot.model.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserLoginService implements UserDetailsService{

	@Autowired
	private UserLoginRepository userLoginRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// get username from repository class
		UserLogin user = userLoginRepo.findByUserName(username);  //returns the username
		System.out.println(user);
		if(user==null)
			throw new UsernameNotFoundException("User does not exist");
		
		
		return new UserPrincipal(user);
	}

}

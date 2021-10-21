package org.impelsys.SpringBoot.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.impelsys.SpringBoot.data.UserRepository;
import org.impelsys.SpringBoot.exception.UserNotFoundException;
import org.impelsys.SpringBoot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;

	public List<User> getAllUsers() {
		List<User> userList = new ArrayList();
		 userRepository.findAll().forEach(userList::add);
		 return userList;
	}

	 public User getUser(int id)  {
		//Optional<User> user = userRepository.findById(id);  //encapsulated user inside optional
		 try {
			 User user = userRepository.findById(id).orElseThrow(()->new UserNotFoundException(id));
			 return user;
		 }
		 catch(UserNotFoundException e)
		 {
			 System.out.println("In catch");
			 throw (e);
			
		 }
		
	}

	public User saveUser(User user) {
		System.out.println("User" + user);
		return userRepository.save(user);
	}


}

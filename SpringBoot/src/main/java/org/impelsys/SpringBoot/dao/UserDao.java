package org.impelsys.SpringBoot.dao;

import java.util.List;

import org.hibernate.Session;
import org.impelsys.SpringBoot.model.User;

public interface UserDao {
	
	int addUser(User user);
	void deleteUser(int id);
	User viewUser(int id);
	List<User> listUsers();
	

}

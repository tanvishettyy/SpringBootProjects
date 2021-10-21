package org.impelsys.SpringBoot.dao;

import org.impelsys.SpringBoot.model.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;

//@repository not required it has default implementation
public interface UserLoginRepository extends JpaRepository<UserLogin, Integer> {

	UserLogin findByUserName(String username);  //finder API
}

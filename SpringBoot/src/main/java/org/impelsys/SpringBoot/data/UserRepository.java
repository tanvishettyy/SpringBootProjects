package org.impelsys.SpringBoot.data;

import org.impelsys.SpringBoot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

//@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Integer> {

}

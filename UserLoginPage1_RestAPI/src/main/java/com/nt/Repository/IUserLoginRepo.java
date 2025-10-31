package com.nt.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nt.entity.User;

@Repository
public interface IUserLoginRepo extends JpaRepository<User,Long>{
//User findByUsernameAndPassword(String username, String password );
User existsByUsername(String username);
//List<User> save(String username, String password, String email, Long mobile);
User findByUsernameAndPassword(String username,String password);
//User findByUsername(String username);
User findByUsername(String username);
}

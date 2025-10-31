package com.nt.Service.ServiceImpl;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nt.Repository.IUserLoginRepo;
import com.nt.Service.IUserLoginService;
import com.nt.entity.Login;
import com.nt.entity.User;

@Service
public class UserLoginServiceImpl implements IUserLoginService, UserDetailsService {

    @Autowired
    private IUserLoginRepo userRepo;

    // You can directly use this encoder — no need to autowired SecurityConfig
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);//encrypt the pasword and verify while login

    @Override
    public String registerUser(User user) {
        User existingUser = userRepo.findByUsername(user.getUsername());
        if (existingUser != null) {
            return "User already exists!";
        }

        user.setPassword(encoder.encode(user.getPassword()));
        Login login = new Login();
        login.setUsername(user.getUsername());
        login.setPassword(user.getPassword());
        user.setLogin(login);
        userRepo.save(user);
        return "User registered successfully!";
    }


    @Override
    public User login(String username, String password) {
        User user = userRepo.findByUsername(username);
        if (user != null && encoder.matches(password, user.getPassword())) {
            return user;
        }
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(
            user.getUsername(),
            user.getPassword(),
            Collections.emptyList()
        );
    }

//	@Override
//	public UserDetails loadUserByUsername(String username, String password) throws UsernameNotFoundException {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public User findByUsername(String username) {
		
		return userRepo.findByUsername(username);
		
	
	

	}
}

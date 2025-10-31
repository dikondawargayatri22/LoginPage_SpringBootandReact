package com.nt.Service;

import com.nt.entity.User;

public interface IUserLoginService {
	public String registerUser(User user);

	//UserDetails loadUserByUsername(String username, String password) throws UsernameNotFoundException;

	//public User save(User user)
	User login(String username, String password);

	//public User save(User user);

	public User findByUsername(String username);
}

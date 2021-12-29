package com.example.demo;

import com.example.demo.dao.CustomerDAO;
import com.example.demo.entity.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private CustomerDAO repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Customer user = repo.findUser(username);
		if (user == null)
			throw new UsernameNotFoundException("User 404");

		return new UserPrincipal(user);
	}

}

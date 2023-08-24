package org.example.news_manager.service.impl;

import org.example.news_manager.dao.UserDAO;
import org.example.news_manager.entity.UserEntity;
import org.example.news_manager.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	private final UserDAO userDAO;

	@Autowired
	public UserDetailsServiceImpl(UserDAO userDAO){
		this.userDAO = userDAO;
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		UserEntity userEntity = userDAO.findUserByUsername(s);
		UserDetailsImpl u = new UserDetailsImpl(userEntity);
		System.out.println(u.getAuthorities());
		if (userEntity == null){
			throw new UsernameNotFoundException("User not found");
		}
		return new UserDetailsImpl(userEntity);
	}
}

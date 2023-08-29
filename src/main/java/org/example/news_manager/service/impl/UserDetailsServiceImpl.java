package org.example.news_manager.service.impl;

import org.example.news_manager.models.bean.UserDetailsImplBean;
import org.example.news_manager.dao.UserDAO;
import org.example.news_manager.models.entity.UserEntity;
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
	public UserDetailsServiceImpl(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		UserEntity userEntity = userDAO.findUserByUsername(s);
		if (userEntity == null) {
			throw new UsernameNotFoundException("User not found");
		}
		UserDetailsImplBean user = new UserDetailsImplBean(userEntity);
		return user;
	}
}

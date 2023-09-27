package org.example.newsmanager.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.newsmanager.models.bean.UserDetailsImplBean;
import org.example.newsmanager.models.entity.UserEntity;
import org.example.newsmanager.repository.UserDAO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
	private final UserDAO userDAO;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		Optional<UserEntity> userEntity = userDAO.findUserByUsername(s);
		if (userEntity.isEmpty()) {
			throw new UsernameNotFoundException("User not found");
		}
		return new UserDetailsImplBean(userEntity.get());
	}
}

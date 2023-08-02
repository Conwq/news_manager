package org.example.service.impl;

import org.example.dao.UserDAO;
import org.example.dao.exception.DAOException;
import org.example.service.UserService;
import org.example.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bean.User;

@Service
public class UserServiceImpl implements UserService{
	private final UserDAO userDAO;
	
	@Autowired
	public UserServiceImpl(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	@Override
	public User signIn(String login, String password) throws ServiceException {
		try {
			User user = userDAO.signIn(login, password);
			return user;
		}
		catch(DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void registration(User user) throws ServiceException {
		try {
			userDAO.registration(user);
		}
		catch(DAOException e) {
			throw new ServiceException(e);
		}
	}
}

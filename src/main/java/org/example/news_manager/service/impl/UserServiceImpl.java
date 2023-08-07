package org.example.news_manager.service.impl;

import org.example.news_manager.dao.UserDAO;
import org.example.news_manager.dao.exception.DAOException;
import org.example.news_manager.service.UserService;
import org.example.news_manager.service.exception.ServiceException;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.example.news_manager.bean.User;

import java.util.Locale;

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
	public void registration(User user, String confirmPassword, String country) throws ServiceException {
		try {
			if(!user.getPassword().equals(confirmPassword)) {
				throw new ServiceException("Password not confirm");
			}
			Locale locale = new Locale("ru", "RU");
			if (country.equals("us")){
				locale = Locale.US;
			}
			String password = user.getPassword();
			String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
			user.setPassword(hashedPassword);
			user.setLocale(locale);
			userDAO.registration(user);
		}
		catch(DAOException e) {
			throw new ServiceException(e);
		}
	}
}

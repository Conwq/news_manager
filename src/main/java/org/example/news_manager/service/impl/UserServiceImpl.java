package org.example.news_manager.service.impl;

import org.example.news_manager.dao.UserDAO;
import org.example.news_manager.dao.exception.DAOException;
import org.example.news_manager.entity.UserEntity;
import org.example.news_manager.service.UserService;
import org.example.news_manager.service.exception.ServiceException;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.example.news_manager.bean.UserBean;
import org.example.news_manager.bean.util.UserMapper;

import java.util.Locale;

@Service
public class UserServiceImpl implements UserService{
	private final UserDAO userDAO;
	private final UserMapper userMapper;
	
	@Autowired
	public UserServiceImpl(UserDAO userDAO, UserMapper userMapper) {
		this.userDAO = userDAO;
		this.userMapper = userMapper;
	}
	
	@Override
	@Transactional
	public UserBean signIn(String login, String password) throws ServiceException {
		try {
			UserEntity userEntity = userDAO.signIn(login, password);
			return userMapper.convertToUserBean(userEntity);
		}
		catch(DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	@Transactional
	public void registration(UserBean userBean, String confirmPassword, String country) throws ServiceException {
		try {
			if(!userBean.getPassword().equals(confirmPassword)) {
				throw new ServiceException("Password not confirm");
			}
			Locale locale = new Locale("ru", "RU");
			if (country.equals("us")){
				locale = Locale.US;
			}
			String password = userBean.getPassword();
			String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
			userBean.setPassword(hashedPassword);
			userBean.setLocale(locale);
			
			UserEntity userEntity = userMapper.convertToUserEntity(userBean);
			
			userDAO.registration(userEntity);
		}
		catch(DAOException e) {
			throw new ServiceException(e);
		}
	}
}

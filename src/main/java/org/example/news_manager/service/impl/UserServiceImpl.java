package org.example.news_manager.service.impl;

import org.example.news_manager.models.bean.UserRegistrationDataBean;
import org.example.news_manager.dao.UserDAO;
import org.example.news_manager.dao.exception.DAOException;
import org.example.news_manager.models.entity.UserEntity;
import org.example.news_manager.service.UserService;
import org.example.news_manager.service.exception.ServiceException;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService{
	private final UserDAO userDAO;

	@Autowired
	public UserServiceImpl(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	@Transactional
	public void registration(UserRegistrationDataBean user) throws ServiceException {
		try {
			String password = user.getPassword();
			if(!password.equals(user.getConfirmPassword())) {
				throw new ServiceException("Password not confirm");
			}

			String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
			user.setPassword(hashedPassword);
			UserEntity userEntity = new UserEntity();

			userEntity.setEmail(user.getEmail());
			userEntity.setLogin(user.getLogin());
			userEntity.setPassword(user.getPassword());

			userDAO.registration(userEntity, user.getLocaleId());
		}
		catch(DAOException e) {
			throw new ServiceException(e);
		}
	}
}

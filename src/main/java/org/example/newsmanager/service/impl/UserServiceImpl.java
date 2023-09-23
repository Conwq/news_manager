package org.example.newsmanager.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.newsmanager.models.bean.UserRegistrationDataBean;
import org.example.newsmanager.models.entity.UserEntity;
import org.example.newsmanager.repository.UserDAO;
import org.example.newsmanager.repository.exception.DAOException;
import org.example.newsmanager.service.UserService;
import org.example.newsmanager.service.exception.ServiceException;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
	private final UserDAO userDAO;

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

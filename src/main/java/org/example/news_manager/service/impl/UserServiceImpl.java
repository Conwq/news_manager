package org.example.news_manager.service.impl;

import org.example.news_manager.dao.UserDAO;
import org.example.news_manager.dao.exception.DAOException;
import org.example.news_manager.dto.UserDTO;
import org.example.news_manager.dto.UserDTOForRegistration;
import org.example.news_manager.entity.UserEntity;
import org.example.news_manager.service.UserService;
import org.example.news_manager.service.exception.ServiceException;
import org.example.news_manager.service.util.mapper.Mapper;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService{
	private final UserDAO userDAO;
	private final Mapper<UserDTO, UserEntity> mapper;
	
	@Autowired
	public UserServiceImpl(UserDAO userDAO,
						   @Qualifier(value = "userMap") Mapper<UserDTO, UserEntity> mapper) {
		this.userDAO = userDAO;
		this.mapper = mapper;
	}
	
	@Override
	@Transactional
	public UserDTO signIn(String login, String password) throws ServiceException {
		try {
			UserEntity userEntity = userDAO.signIn(login, password);
			return mapper.mapToDTO(userEntity);
		}
		catch(DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	@Transactional
	public void registration(UserDTOForRegistration user) throws ServiceException {
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

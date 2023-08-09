package org.example.news_manager.service.impl;

import org.example.news_manager.dao.UserDAO;
import org.example.news_manager.dao.exception.DAOException;
import org.example.news_manager.dto.UserDTO;
import org.example.news_manager.entity.UserEntity;
import org.example.news_manager.service.UserService;
import org.example.news_manager.service.exception.ServiceException;
import org.example.news_manager.service.util.mapper.Mapper;
import org.example.news_manager.service.util.mapper.impl.UserMapper;
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
						   @Qualifier(value = "userMapper") Mapper<UserDTO, UserEntity> mapper) {
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
	public void registration(UserDTO userBean, String confirmPassword, String localeName) throws ServiceException {
		try {
			String password = userBean.getPassword();
			if(!password.equals(confirmPassword)) {
				throw new ServiceException("Password not confirm");
			}

			int localeId = localeName.equals("ru_RU") ? 1 : 2;

			String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
			userBean.setPassword(hashedPassword);
			UserEntity userEntity = mapper.mapToEntity(userBean);
			userDAO.registration(userEntity, localeId);
		}
		catch(DAOException e) {
			throw new ServiceException(e);
		}
	}
}

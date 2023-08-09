package org.example.news_manager.service;

import org.example.news_manager.dto.UserDTO;
import org.example.news_manager.service.exception.ServiceException;

public interface UserService {
	UserDTO signIn(String login, String password) throws ServiceException;
	void registration(UserDTO userBean, String confirmPassword, String localeName) throws ServiceException;
}

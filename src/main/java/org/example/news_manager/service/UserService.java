package org.example.news_manager.service;

import org.example.news_manager.bean.UserInfoBean;
import org.example.news_manager.bean.UserRegistrationDataBean;
import org.example.news_manager.service.exception.ServiceException;

public interface UserService {
	UserInfoBean signIn(String login, String password) throws ServiceException;
	void registration(UserRegistrationDataBean user) throws ServiceException;
}

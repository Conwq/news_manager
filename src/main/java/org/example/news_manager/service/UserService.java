package org.example.news_manager.service;

import org.example.news_manager.service.exception.ServiceException;

import org.example.news_manager.bean.UserBean;
import org.example.news_manager.entity.UserEntity;

public interface UserService {
	UserBean signIn(String login, String password) throws ServiceException;
	void registration(UserBean userBean, String confirmPassword, String country) throws ServiceException;
}

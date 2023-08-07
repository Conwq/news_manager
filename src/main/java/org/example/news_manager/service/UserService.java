package org.example.news_manager.service;

import org.example.news_manager.service.exception.ServiceException;

import org.example.news_manager.bean.User;

public interface UserService {
	User signIn(String login, String password) throws ServiceException;
	void registration(User user, String confirmPassword, String country) throws ServiceException;
}

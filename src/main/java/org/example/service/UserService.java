package org.example.service;

import org.example.service.exception.ServiceException;

import org.example.bean.User;

public interface UserService {
	User signIn(String login, String password) throws ServiceException;
	void registration(User user, String confirmPassword, String country) throws ServiceException;
}

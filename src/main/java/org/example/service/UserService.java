package org.example.service;

import org.example.service.exception.ServiceException;

import org.example.entity.User;

public interface UserService {
	User signIn(String login, String password) throws ServiceException;
	void registration(User user, String confirmPassword) throws ServiceException;
}

package org.example.dao;

import org.example.dao.exception.DAOException;

import org.example.bean.User;

public interface UserDAO {
	User signIn(String login, String password) throws DAOException;
	void registration(User user) throws DAOException;
}

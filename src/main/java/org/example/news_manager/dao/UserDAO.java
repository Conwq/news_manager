package org.example.news_manager.dao;

import org.example.news_manager.dao.exception.DAOException;

import org.example.news_manager.bean.User;

public interface UserDAO {
	User signIn(String login, String password) throws DAOException;
	void registration(User user) throws DAOException;
}

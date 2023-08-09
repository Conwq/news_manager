package org.example.news_manager.dao;

import org.example.news_manager.dao.exception.DAOException;
import org.example.news_manager.entity.UserEntity;

public interface UserDAO {
	UserEntity signIn(String login, String password) throws DAOException;
	void registration(UserEntity userEntity, int localeId) throws DAOException;
}

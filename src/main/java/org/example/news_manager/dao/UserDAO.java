package org.example.news_manager.dao;

import org.example.news_manager.dao.exception.DAOException;
import org.example.news_manager.entity.UserEntity;

public interface UserDAO {
	void registration(UserEntity userEntity, int localeId) throws DAOException;
	UserEntity findUserByUsername(String username);
}

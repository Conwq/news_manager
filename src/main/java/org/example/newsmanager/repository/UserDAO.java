package org.example.newsmanager.repository;

import org.example.newsmanager.repository.exception.DAOException;
import org.example.newsmanager.models.entity.UserEntity;

public interface UserDAO {
	void registration(UserEntity userEntity, int localeId) throws DAOException;
	UserEntity findUserByUsername(String username);
}

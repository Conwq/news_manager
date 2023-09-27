package org.example.newsmanager.repository;

import org.example.newsmanager.repository.exception.DAOException;
import org.example.newsmanager.models.entity.UserEntity;

import java.util.Optional;

public interface UserDAO {
	void registration(UserEntity userEntity, int localeId) throws DAOException;
	Optional<UserEntity> findUserByUsername(String username);
}

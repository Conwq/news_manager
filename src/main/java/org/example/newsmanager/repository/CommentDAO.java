package org.example.newsmanager.repository;

import org.example.newsmanager.models.entity.CommentEntity;
import org.example.newsmanager.models.entity.NewsEntity;
import org.example.newsmanager.models.entity.UserEntity;
import org.example.newsmanager.repository.exception.DAOException;

import java.util.List;
import java.util.Optional;

public interface CommentDAO {
	void saveComment(CommentEntity commentEntity) throws DAOException;
	List<CommentEntity> getAllCommentsByNewsId(int newsId) throws DAOException;
	void deleteCommentById(int commentId) throws DAOException;
	Optional<CommentEntity> getCommentById(int commentId) throws DAOException;
	void editCommentById(int id, String text) throws DAOException;
}

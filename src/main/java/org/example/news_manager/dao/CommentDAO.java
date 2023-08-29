package org.example.news_manager.dao;

import org.example.news_manager.dao.exception.DAOException;
import org.example.news_manager.models.entity.CommentEntity;

import java.util.List;

public interface CommentDAO {
	void saveComment(CommentEntity commentEntity, String username, int newsId) throws DAOException;
	List<CommentEntity> getCommentsFromNewsById(int newsId) throws DAOException;
	void deleteCommentById(int commentId) throws DAOException;
	CommentEntity getCommentById(int commentId) throws DAOException;
	void editCommentById(int id, String text) throws DAOException;
}

package org.example.news_manager.dao;

import java.util.List;

import org.example.news_manager.dao.exception.DAOException;
import org.example.news_manager.entity.CommentEntity;

public interface CommentDAO {
	void saveComment(CommentEntity commentEntity, int userId, int newsId) throws DAOException;
	List<CommentEntity> getCommentsFromNewsById(int newsId) throws DAOException;
	void deleteCommentById(int commentId) throws DAOException;
	CommentEntity getCommentById(int commentId) throws DAOException;
}

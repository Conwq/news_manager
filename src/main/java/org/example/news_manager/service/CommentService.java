package org.example.news_manager.service;

import java.util.List;
import java.util.Locale;

import org.example.news_manager.dto.CommentDTO;
import org.example.news_manager.service.exception.ServiceException;

public interface CommentService {
	void saveComment(String text, int userId, String newsId) throws ServiceException;
	List<CommentDTO> getCommentsFromNewsById(String newsId, Locale locale) throws ServiceException;
	void deleteCommentById(String commentId) throws ServiceException;
	CommentDTO getCommentById(String commentId) throws ServiceException;
}
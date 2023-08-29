package org.example.news_manager.service;

import org.example.news_manager.models.bean.CommentDataForEditBean;
import org.example.news_manager.models.bean.CommentInfoBean;
import org.example.news_manager.service.exception.ServiceException;

import java.util.List;
import java.util.Locale;

public interface CommentService {
	void saveComment(String text, String username, String newsId) throws ServiceException;
	List<CommentInfoBean> getCommentsFromNewsById(String newsId, Locale locale) throws ServiceException;
	void deleteCommentById(String commentId) throws ServiceException;
	CommentDataForEditBean getCommentById(String commentId) throws ServiceException;
	void editCommentById(String commentId, String text) throws ServiceException;
}

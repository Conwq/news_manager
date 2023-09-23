package org.example.newsmanager.service;

import org.example.newsmanager.models.bean.CommentDataForEditBean;
import org.example.newsmanager.models.bean.CommentInfoBean;
import org.example.newsmanager.service.exception.ServiceException;

import java.util.List;
import java.util.Locale;

public interface CommentService {
	void saveComment(String text, String username, String newsId) throws ServiceException;
	List<CommentInfoBean> getCommentsFromNewsById(String newsId, Locale locale) throws ServiceException;
	void deleteCommentById(String commentId) throws ServiceException;
	CommentDataForEditBean getCommentById(String commentId) throws ServiceException;
	void editCommentById(String commentId, String text) throws ServiceException;
}

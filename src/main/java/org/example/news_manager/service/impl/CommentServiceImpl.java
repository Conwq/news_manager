package org.example.news_manager.service.impl;

import org.example.news_manager.bean.CommentDataForEditBean;
import org.example.news_manager.bean.CommentInfoBean;
import org.example.news_manager.dao.CommentDAO;
import org.example.news_manager.dao.exception.DAOException;
import org.example.news_manager.entity.CommentEntity;
import org.example.news_manager.service.CommentService;
import org.example.news_manager.service.exception.ServiceException;
import org.example.news_manager.service.util.converter.DateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class CommentServiceImpl implements CommentService {
	private final CommentDAO commentDAO;
	private final DateConverter dateConverter;

	@Autowired
	public CommentServiceImpl(CommentDAO commentDAO,
							  DateConverter dateConverter) {
		this.commentDAO = commentDAO;
		this.dateConverter = dateConverter;
	}
	
	@Override
	@Transactional
	public void saveComment(String text, String username, String newsId) throws ServiceException{
		try {
			int id = Integer.parseInt(newsId);
			CommentEntity commentEntity = new CommentEntity();
			commentEntity.setText(text);
			
			commentDAO.saveComment(commentEntity, username, id);
		}
		catch(DAOException | IllegalArgumentException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	@Transactional
	public List<CommentInfoBean> getCommentsFromNewsById(String newsId, Locale locale) throws ServiceException {
		try {
			int id = Integer.parseInt(newsId);
			List<CommentEntity> commentsEntity = commentDAO.getCommentsFromNewsById(id);
			List<CommentInfoBean> comments = new ArrayList<>();
			
			for(CommentEntity comment: commentsEntity) {
				CommentInfoBean commentInfo = new CommentInfoBean();
				commentInfo.setId(comment.getId());
				commentInfo.setText(comment.getText());
				commentInfo.setUsername(comment.getUserEntity().getLogin());
				String formatPublicationDate = dateConverter.getFormatDateByComment(comment.getPublicationDate(), locale);
				commentInfo.setPublicationDate(formatPublicationDate);
				comments.add(commentInfo);
			}
			return comments;
		}
		catch(DAOException | IllegalArgumentException e) {
			throw new ServiceException(e);
		}
	}
	
	@Override
	@Transactional
	public void deleteCommentById(String commentId) throws ServiceException {
		try {
			int id = Integer.parseInt(commentId);
			commentDAO.deleteCommentById(id);
		}
		catch(IllegalArgumentException | DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	@Override
	@Transactional
	public CommentDataForEditBean getCommentById(String commentId) throws ServiceException{
		try {
			int id = Integer.parseInt(commentId);
			CommentEntity commentEntity = commentDAO.getCommentById(id);
			CommentDataForEditBean comment = new CommentDataForEditBean();
			comment.setId(commentEntity.getId());
			comment.setText(commentEntity.getText());
			return comment;
		}
		catch(DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	@Transactional
	public void editCommentById(String commentId, String text) throws ServiceException {
		try{
			int id = Integer.parseInt(commentId);
			commentDAO.editCommentById(id, text);
		}
		catch (DAOException e){
			throw new ServiceException(e);
		}
	}
}

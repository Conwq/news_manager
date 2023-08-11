package org.example.news_manager.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.example.news_manager.dao.CommentDAO;
import org.example.news_manager.dao.exception.DAOException;
import org.example.news_manager.dto.CommentDTO;
import org.example.news_manager.entity.CommentEntity;
import org.example.news_manager.service.CommentService;
import org.example.news_manager.service.exception.ServiceException;
import org.example.news_manager.service.util.converter.DateConverter;
import org.example.news_manager.service.util.mapper.Mapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentServiceImpl implements CommentService {
	private final Mapper<CommentDTO, CommentEntity> mapper;
	private final CommentDAO commentDAO;
	private final DateConverter dateConverter;
	
	public CommentServiceImpl(CommentDAO commentDAO, @Qualifier("commentMap") Mapper<CommentDTO, CommentEntity> mapper, DateConverter dateConverter) {
		this.commentDAO = commentDAO;
		this.mapper = mapper;
		this.dateConverter = dateConverter;
	}
	
	@Override
	@Transactional
	public void saveComment(String text, int userId, String newsId) throws ServiceException{
		try {
			int id = Integer.parseInt(newsId);
			CommentEntity commentEntity = new CommentEntity();
			commentEntity.setText(text);
			
			commentDAO.saveComment(commentEntity, userId, id);
		}
		catch(DAOException | IllegalArgumentException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	@Transactional
	public List<CommentDTO> getCommentsFromNewsById(String newsId, Locale locale) throws ServiceException {
		try {
			int id = Integer.parseInt(newsId);
			List<CommentEntity> commentsEntity = commentDAO.getCommentsFromNewsById(id);
			
			List<CommentDTO> commentsDTO = new ArrayList<>();
			
			for(CommentEntity comment: commentsEntity) {
				CommentDTO commentDTO = mapper.mapToDTO(comment);
				dateConverter.getFormatDateByComment(commentDTO, locale);
				commentsDTO.add(commentDTO);
			}
			
			return commentsDTO;
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
	public CommentDTO getCommentById(String commentId) throws ServiceException{
		try {
			int id = Integer.parseInt(commentId);
			CommentEntity commentEntity = commentDAO.getCommentById(id);
			return mapper.mapToDTO(commentEntity);
		}
		catch(DAOException e) {
			throw new ServiceException(e);
		}
	}
}

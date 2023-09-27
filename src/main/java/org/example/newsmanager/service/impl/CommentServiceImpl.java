package org.example.newsmanager.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.newsmanager.models.bean.CommentResponse;
import org.example.newsmanager.models.entity.CommentEntity;
import org.example.newsmanager.repository.CommentDAO;
import org.example.newsmanager.repository.exception.DAOException;
import org.example.newsmanager.service.CommentService;
import org.example.newsmanager.service.exception.ServiceException;
import org.example.newsmanager.service.util.converter.DateConverter;
import org.example.newsmanager.service.util.mapper.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
	private final CommentDAO commentDAO;
	private final Mapper <CommentEntity, CommentResponse> mapper;

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
	@Transactional(readOnly = true)
	public List<CommentResponse> getCommentsFromNewsById(String newsId, Locale locale) throws ServiceException {
		try {
			int id = Integer.parseInt(newsId);
			List<CommentEntity> commentsEntity = commentDAO.getCommentsFromNewsById(id);

			return commentsEntity.stream()
					.map(commentEntity -> mapper.mapToDto(commentEntity, locale))
					.toList();
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

			Optional<CommentEntity> optionalComment = commentDAO.getCommentById(id);
			if (optionalComment.isEmpty()){
				throw new ServiceException("Comment with id `" + commentId + "` not found.");
			}

			commentDAO.deleteCommentById(id);
		}
		catch(IllegalArgumentException | DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	@Override
	@Transactional(readOnly = true)
	public CommentResponse getCommentById(String commentId) throws ServiceException{
		try {
			int id = Integer.parseInt(commentId);
			Optional<CommentEntity> optionalCommentEntity = commentDAO.getCommentById(id);

			if (optionalCommentEntity.isEmpty()){
				throw new ServiceException("Comment with id `" + commentId + "` not found.");
			}

			return mapper.mapToDto(optionalCommentEntity.get(), null);
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

			Optional<CommentEntity> optionalComment = commentDAO.getCommentById(id);
			if (optionalComment.isEmpty()){
				throw new ServiceException("Comment with id `" + commentId + "` not found.");
			}

			commentDAO.editCommentById(id, text);
		}
		catch (DAOException e){
			throw new ServiceException(e);
		}
	}
}

package org.example.newsmanager.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.newsmanager.models.bean.CommentResponse;
import org.example.newsmanager.models.entity.CommentEntity;
import org.example.newsmanager.models.entity.NewsEntity;
import org.example.newsmanager.models.entity.UserEntity;
import org.example.newsmanager.repository.CommentDAO;
import org.example.newsmanager.repository.NewsDAO;
import org.example.newsmanager.repository.UserDAO;
import org.example.newsmanager.repository.exception.DAOException;
import org.example.newsmanager.service.CommentService;
import org.example.newsmanager.service.exception.ServiceException;
import org.example.newsmanager.service.util.mapper.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
	private final CommentDAO commentDAO;
	private final UserDAO userDAO;
	private final NewsDAO newsDAO;
	private final Mapper <CommentEntity, CommentResponse> mapper;

	@Override
	@Transactional
	public void saveComment(String text, String username, String newsId) throws ServiceException{
		try {
			if (text == null || username == null || newsId == null){
				throw new ServiceException("Data cannot be empty.");
			}
			int id = Integer.parseInt(newsId);
			Optional<NewsEntity> optionalNews = newsDAO.findById(id);
			Optional<UserEntity> optionalUser = userDAO.findUserByUsername(username);

			if (optionalNews.isEmpty() || optionalUser.isEmpty()){
				throw new ServiceException("Incorrect data.");
			}

			commentDAO.saveComment(createCommentEntity(text, optionalNews.get(), optionalUser.get()));
		}
		catch(DAOException | IllegalArgumentException e) {
			throw new ServiceException(e);
		}
	}

	private CommentEntity createCommentEntity(String text, NewsEntity news, UserEntity user) throws ServiceException {
		CommentEntity comment = new CommentEntity();
		comment.setText(text);
		comment.setNewsEntity(news);
		comment.setUserEntity(user);

		news.getComments().add(comment);
		user.getComments().add(comment);

		return comment;
	}

	@Override
	@Transactional(readOnly = true)
	public List<CommentResponse> getAllCommentsByNewsId(String newsId, Locale locale) throws ServiceException {
		try {
			int id = Integer.parseInt(newsId);
			List<CommentEntity> commentsEntity = commentDAO.getAllCommentsByNewsId(id);

			return mapper.mapListToDto(commentsEntity, locale);
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

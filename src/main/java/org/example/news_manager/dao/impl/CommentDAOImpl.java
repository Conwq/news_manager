package org.example.news_manager.dao.impl;

import org.example.news_manager.dao.CommentDAO;
import org.example.news_manager.dao.exception.DAOException;
import org.example.news_manager.models.entity.CommentEntity;
import org.example.news_manager.models.entity.NewsEntity;
import org.example.news_manager.models.entity.UserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentDAOImpl implements CommentDAO{
	private final SessionFactory sessionFactory;

	public CommentDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void saveComment(CommentEntity commentEntity, String username, int newsId) throws DAOException{
		try {
			Session session = sessionFactory.getCurrentSession();
			
			Query query = session.createQuery("FROM UserEntity WHERE login LIKE (:username)", UserEntity.class);
			query.setParameter("username", username);
			UserEntity user = (UserEntity) query.getSingleResult();
			
			NewsEntity news = session.get(NewsEntity.class, newsId);
			commentEntity.setNewsEntity(news);
			commentEntity.setUserEntity(user);
			user.getComments().add(commentEntity);
			news.getComments().add(commentEntity);

			session.save(commentEntity);
		}
		catch (Exception e){
			throw new DAOException(e);
		}
	}
	
	@Override
	public List<CommentEntity> getCommentsFromNewsById(int newsId) throws DAOException{
		try {
			Session session = sessionFactory.getCurrentSession();
			NewsEntity newsEntity = session.get(NewsEntity.class, newsId);
			return newsEntity.getComments();
		}
		catch (Exception e){
			throw new DAOException(e);
		}
	}
	
	@Override
	public void deleteCommentById(int commentId) throws DAOException{
		try {
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("DELETE FROM CommentEntity WHERE id = :id");
			query.setParameter("id", commentId);
			query.executeUpdate();
		}
		catch (Exception e){
			throw new DAOException(e);
		}
	}
	
	@Override
	public CommentEntity getCommentById(int commentId) throws DAOException{
		try {
			Session session = sessionFactory.getCurrentSession();
			return session.get(CommentEntity.class, commentId);
		}
		catch (Exception e){
			throw new DAOException(e);
		}
	}

	@Override
	public void editCommentById(int id, String text) throws DAOException {
		try {
			Session session = sessionFactory.getCurrentSession();
			CommentEntity comment = session.get(CommentEntity.class, id);
			comment.setText(text);
		}
		catch (Exception e){
			throw new DAOException(e);
		}
	}
}

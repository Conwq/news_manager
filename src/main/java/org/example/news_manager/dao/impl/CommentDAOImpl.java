package org.example.news_manager.dao.impl;

import java.util.List;

import org.example.news_manager.dao.CommentDAO;
import org.example.news_manager.entity.CommentEntity;
import org.example.news_manager.entity.NewsEntity;
import org.example.news_manager.entity.UserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class CommentDAOImpl implements CommentDAO{
	private final SessionFactory sessionFactory;

	public CommentDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void saveComment(CommentEntity commentEntity, int userId, int newsId) {
		Session session = sessionFactory.getCurrentSession();
		UserEntity user = session.get(UserEntity.class, userId);
		NewsEntity news = session.get(NewsEntity.class, newsId);
		commentEntity.setNewsEntity(news);
		commentEntity.setUserEntity(user);
		user.getComments().add(commentEntity);
		news.getComments().add(commentEntity);
		
		session.save(commentEntity);
	}
	
	@Override
	public List<CommentEntity> getCommentsFromNewsById(int newsId){
		Session session = sessionFactory.getCurrentSession();
		NewsEntity newsEntity = session.get(NewsEntity.class, newsId);
		List<CommentEntity> comments = newsEntity.getComments();
		return comments;
	}
	
	@Override
	public void deleteCommentById(int commentId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("DELETE FROM CommentEntity WHERE id = :id");
		query.setParameter("id", commentId);
		query.executeUpdate();
	}
	
	@Override
	public CommentEntity getCommentById(int commentId) {
		Session session = sessionFactory.getCurrentSession();
		CommentEntity comment = session.get(CommentEntity.class, commentId);
		return comment;
	}
}

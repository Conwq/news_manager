package org.example.newsmanager.repository.impl;

import lombok.RequiredArgsConstructor;
import org.example.newsmanager.models.entity.CommentEntity;
import org.example.newsmanager.models.entity.NewsEntity;
import org.example.newsmanager.repository.CommentDAO;
import org.example.newsmanager.repository.exception.DAOException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CommentDAOImpl implements CommentDAO{
	private final SessionFactory sessionFactory;

	@Override
	public void saveComment(CommentEntity commentEntity) throws DAOException{
		try {
			Session session = sessionFactory.getCurrentSession();
			session.save(commentEntity);
		}
		catch (Exception e){
			throw new DAOException(e);
		}
	}
	
	@Override
	public List<CommentEntity> getAllCommentsByNewsId(int newsId) throws DAOException{
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
	public Optional<CommentEntity> getCommentById(int commentId) throws DAOException{
		try {
			Session session = sessionFactory.getCurrentSession();
			return Optional.ofNullable(session.get(CommentEntity.class, commentId));
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

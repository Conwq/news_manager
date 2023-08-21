package org.example.news_manager.dao.impl;

import org.example.news_manager.dao.TagDAO;
import org.example.news_manager.dao.exception.DAOException;
import org.example.news_manager.entity.TagEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class TagDAOImpl implements TagDAO {
	private final SessionFactory sessionFactory;

	@Autowired
	public TagDAOImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}


	@Override
	public List<TagEntity> getAllTags() throws DAOException {
		try {
			Session session = sessionFactory.getCurrentSession();
			return session.createQuery("FROM TagEntity", TagEntity.class).getResultList();
		}
		catch (Exception e){
			throw new DAOException(e);
		}
	}

	@Override
	public TagEntity findById(int tagId) throws DAOException {
		try{
			Session session = sessionFactory.getCurrentSession();
			return session.get(TagEntity.class, tagId);
		}
		catch(Exception e){
			throw new DAOException(e);
		}
	}

	@Override
	public List<TagEntity> getTagsForNewsById(int newsId) throws DAOException {
		try{
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("SELECT t FROM TagEntity AS t JOIN FETCH t.news AS n WHERE n.id = :newsId",
					TagEntity.class);
			query.setParameter("newsId", newsId);
			return query.getResultList();
		}
		catch (Exception e){
			throw new DAOException(e);
		}
	}
}

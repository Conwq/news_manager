package org.example.news_manager.dao.impl;

import org.example.news_manager.dao.NewsDAO;
import org.example.news_manager.dao.exception.DAOException;
import org.example.news_manager.entity.NewsEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StaleStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class NewsDAOImpl implements NewsDAO{
	private final SessionFactory sessionFactory;

	@Autowired
	public NewsDAOImpl (SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<NewsEntity> getNews() throws DAOException{
		try {
			Session session = sessionFactory.getCurrentSession();
			List<NewsEntity> newsList = session.createQuery("FROM NewsEntity ORDER BY publicationDate DESC", NewsEntity.class)
					.getResultList();
			return newsList;
		}
		catch (HibernateException e){
			throw new DAOException(e);
		}
	}

	@Override
	public List<NewsEntity> getNews(int count) throws DAOException{
		try {
			Session session = sessionFactory.getCurrentSession();
			List<NewsEntity> newsList = session.createQuery("FROM NewsEntity ORDER BY publicationDate DESC", NewsEntity.class)
					.setMaxResults(count).getResultList();
			return newsList;
		}
		catch (HibernateException e){
			throw new DAOException(e);
		}
	}

	@Override
	public List<NewsEntity> getFoundNewsByValue(String value) throws DAOException{
		try{
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("FROM NewsEntity WHERE title LIKE (:value) ORDER BY publicationDate DESC", NewsEntity.class);
			query.setParameter("value", "%" + value + "%");
			return query.getResultList();
		}
		catch (HibernateException e){
			throw new DAOException(e);
		}
	}

	@Override
	public NewsEntity findById(int id) throws DAOException {
		try{
			Session session = sessionFactory.getCurrentSession();
			NewsEntity newsEntity = session.get(NewsEntity.class, id);
			return newsEntity;
		}
		catch (HibernateException e){
			throw new DAOException(e);
		}
	}

	@Override
	public void editNews(NewsEntity newsEntity) throws DAOException {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.update(newsEntity);
		}
		catch (StaleStateException e){
			e.printStackTrace();
			throw new DAOException(e);
		}
	}

	@Override
	public void addNews(NewsEntity newsEntity) throws DAOException {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.save(newsEntity);
		}
		catch (HibernateException e){
			throw new DAOException(e);
		}
	}

	@Override
	public void deleteNewsById(int newsId) throws DAOException {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("DELETE FROM NewsEntity WHERE id=:newsId");
			query.setParameter("newsId", newsId);
			query.executeUpdate();
		}
		catch (HibernateException e){
			throw new DAOException(e);
		}
	}

	@Override
	public void deleteNewsList(List<Integer> idList) throws DAOException {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.createQuery("DELETE FROM NewsEntity WHERE id IN (:id)")
					.setParameterList("id", idList)
					.executeUpdate();
			session.flush();
		}
		catch (HibernateException e){
			throw new DAOException(e);
		}
	}
}

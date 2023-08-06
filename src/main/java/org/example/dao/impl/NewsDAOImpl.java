package org.example.dao.impl;

import org.example.bean.News;
import org.example.dao.NewsDAO;
import org.example.dao.exception.DAOException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
	public List<News> getNewses() throws DAOException{
		try {
			Session session = sessionFactory.getCurrentSession();
			List<News> newsList = session.createQuery("FROM News ORDER BY publicationDate DESC", News.class)
					.getResultList();
			session.clear();
			return newsList;
		}
		catch (HibernateException e){
			throw new DAOException(e);
		}
	}

	@Override
	public List<News> getNewses(int count) throws DAOException{
		Session session = sessionFactory.getCurrentSession();
		List<News> newsList = session.createQuery("FROM News ORDER BY publicationDate DESC", News.class)
				.setMaxResults(count).getResultList();
		session.clear();
		return newsList;
	}

	@Override
	public News findById(int id) throws DAOException {
		try{
			Session session = sessionFactory.getCurrentSession();
			News news = session.get(News.class, id);
			session.clear();
			return news;
		}
		catch (HibernateException e){
			throw new DAOException(e);
		}
	}

	@Override
	public void editNews(News news) throws DAOException {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.update(news);
		}
		catch (HibernateException e){
			throw new DAOException(e);
		}
	}

	@Override
	public void addNews(News news) throws DAOException {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.save(news);
		}
		catch (HibernateException e){
			throw new DAOException(e);
		}
	}

	@Override
	public void deleteNewsById(int newsId) throws DAOException {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("DELETE FROM News WHERE id=:newsId");
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
			session.createQuery("DELETE FROM News WHERE id IN (:id)")
					.setParameterList("id", idList)
					.executeUpdate();
			session.flush();
		}
		catch (HibernateException e){
			throw new DAOException(e);
		}
	}
}

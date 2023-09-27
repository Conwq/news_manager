package org.example.newsmanager.repository.impl;

import lombok.RequiredArgsConstructor;
import org.example.newsmanager.repository.NewsDAO;
import org.example.newsmanager.repository.exception.DAOException;
import org.example.newsmanager.models.entity.NewsEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class NewsDAOImpl implements NewsDAO{
	private final SessionFactory sessionFactory;

	@Override
	public List<NewsEntity> getNews() throws DAOException{
		try {
			Session session = sessionFactory.getCurrentSession();
			return session.createQuery("FROM NewsEntity ORDER BY publicationDate DESC", NewsEntity.class)
					.getResultList();
		}
		catch (Exception e){
			throw new DAOException(e);
		}
	}

	@Override
	public List<NewsEntity> getNews(int count) throws DAOException{
		try {
			Session session = sessionFactory.getCurrentSession();
			return session.createQuery("FROM NewsEntity ORDER BY publicationDate DESC", NewsEntity.class)
					.setMaxResults(count).getResultList();
		}
		catch (Exception e){
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
		catch (Exception e){
			throw new DAOException(e);
		}
	}

	@Override
	public Optional<NewsEntity> findById(int id) throws DAOException {
		try{
			Session session = sessionFactory.getCurrentSession();
			return Optional.of(session.get(NewsEntity.class, id));
		}
		catch (Exception e){
			throw new DAOException(e);
		}
	}

	@Override
	public void editNews(NewsEntity newsEntity) throws DAOException {
		try {
			Session session = sessionFactory.getCurrentSession();
//			session.update(newsEntity);
			session.merge(newsEntity);
		}
		catch (Exception e){
			throw new DAOException(e);
		}
	}

	@Override
	public void addNews(NewsEntity newsEntity) throws DAOException {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.save(newsEntity);
		}
		catch (Exception e){
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
		catch (Exception e){
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
		catch (Exception e){
			throw new DAOException(e);
		}
	}
}

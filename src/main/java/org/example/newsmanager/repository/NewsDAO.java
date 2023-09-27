package org.example.newsmanager.repository;

import org.example.newsmanager.models.entity.NewsEntity;
import org.example.newsmanager.repository.exception.DAOException;

import java.util.List;
import java.util.Optional;

public interface NewsDAO {
	List<NewsEntity> getNews() throws DAOException;
	List<NewsEntity> getNews(int count) throws DAOException;
	List<NewsEntity> getFoundNewsByValue(String value) throws DAOException;
	Optional<NewsEntity> findById(int id) throws DAOException;
	void editNews(NewsEntity news) throws DAOException;
	void addNews(NewsEntity news)throws DAOException;
	void deleteNewsById(int newsId) throws DAOException;
	void deleteNewsList(List<Integer> idList) throws DAOException;
}

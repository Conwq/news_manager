package org.example.news_manager.dao;

import org.example.news_manager.dao.exception.DAOException;
import org.example.news_manager.models.entity.NewsEntity;

import java.util.List;

public interface NewsDAO {
	List<NewsEntity> getNews() throws DAOException;
	List<NewsEntity> getNews(int count) throws DAOException;
	List<NewsEntity> getFoundNewsByValue(String value) throws DAOException;
	NewsEntity findById(int id) throws DAOException;
	void editNews(NewsEntity news) throws DAOException;
	void addNews(NewsEntity news)throws DAOException;
	void deleteNewsById(int newsId) throws DAOException;
	void deleteNewsList(List<Integer> idList) throws DAOException;
}

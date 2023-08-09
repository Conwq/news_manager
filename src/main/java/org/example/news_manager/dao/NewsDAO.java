package org.example.news_manager.dao;

import org.example.news_manager.dao.exception.DAOException;
import org.example.news_manager.entity.NewsEntity;

import java.util.List;

public interface NewsDAO {
	List<NewsEntity> getNewses() throws DAOException;
	List<NewsEntity> getNewses(int count) throws DAOException;
	NewsEntity findById(int id) throws DAOException;
	void editNews(NewsEntity news) throws DAOException;
	void addNews(NewsEntity news)throws DAOException;
	void deleteNewsById(int newsId) throws DAOException;
	void deleteNewsList(List<Integer> idList) throws DAOException;
}

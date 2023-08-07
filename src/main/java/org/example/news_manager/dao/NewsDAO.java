package org.example.news_manager.dao;

import org.example.news_manager.bean.News;
import org.example.news_manager.dao.exception.DAOException;

import java.util.List;

public interface NewsDAO {
	List<News> getNewses() throws DAOException;
	List<News> getNewses(int count) throws DAOException;
	News findById(int id) throws DAOException;
	void editNews(News news) throws DAOException;
	void addNews(News news)throws DAOException;
	void deleteNewsById(int newsId) throws DAOException;
	void deleteNewsList(List<Integer> idList) throws DAOException;
}

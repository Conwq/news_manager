package org.example.dao;

import java.util.List;

import org.example.dao.exception.DAOException;

import bean.News;

public interface NewsDAO {
	List<News> getNewses() throws DAOException;
	List<News> getNewses(int count) throws DAOException;
	News findById(int id) throws DAOException;
	void editNews(News news) throws DAOException;
	void deleteNewsById(int newsId) throws DAOException;
}

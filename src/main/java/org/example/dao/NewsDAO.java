package org.example.dao;

import org.example.entity.News;
import org.example.dao.exception.DAOException;

import java.util.List;

public interface NewsDAO {
	List<News> getNewses() throws DAOException;
	List<News> getNewses(int count) throws DAOException;
	News findById(int id) throws DAOException;
	void editNews(News news) throws DAOException;
	void deleteNewsById(int newsId) throws DAOException;
	void addNews(News news)throws DAOException;
	void deleteNews(int[] someId) throws DAOException;
}

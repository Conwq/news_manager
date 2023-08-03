package org.example.service;

import org.example.bean.News;
import org.example.service.exception.ServiceException;

import java.util.List;

public interface NewsService {
	List<News> getNewses() throws ServiceException;
	List<News> getNewses(String count) throws ServiceException;
	News findById(String id) throws ServiceException;
	void editNews(News news) throws ServiceException;
	void deleteNewsById(String id) throws ServiceException;
	void addNews(News news) throws ServiceException;
	void deleteNews(String[] news) throws ServiceException;
}

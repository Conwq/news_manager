package org.example.service;

import org.example.bean.News;
import org.example.service.exception.ServiceException;

import java.util.List;
import java.util.Locale;

public interface NewsService {
	List<News> getNewses(Locale locale) throws ServiceException;
	List<News> getNewses(String count, Locale locale) throws ServiceException;
	News findById(String id, Locale locale) throws ServiceException;
	News findById(String id) throws ServiceException;
	void editNews(News news) throws ServiceException;
	void addNews(News news) throws ServiceException;
	void deleteNewsById(String id) throws ServiceException;
	void deleteNewsList(String[] news) throws ServiceException;
}

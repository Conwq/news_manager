package org.example.news_manager.service;

import org.example.news_manager.bean.News;
import org.example.news_manager.service.exception.ServiceException;

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

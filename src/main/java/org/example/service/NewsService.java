package org.example.service;

import java.util.List;

import org.example.service.exception.ServiceException;

import org.example.bean.News;

public interface NewsService {
	List<News> getNewses() throws ServiceException;
	List<News> getNewses(String count) throws ServiceException;
	News findById(String id) throws ServiceException;
	void editNews(News news) throws ServiceException;
	void deleteNewsById(String id) throws ServiceException;
}

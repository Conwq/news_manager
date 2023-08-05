package org.example.service.impl;

import org.example.bean.News;
import org.example.dao.NewsDAO;
import org.example.dao.exception.DAOException;
import org.example.service.NewsService;
import org.example.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.IllegalFormatException;
import java.util.List;
import java.util.Locale;

@Service
public class NewsServiceImpl implements NewsService {
	private NewsDAO newsDAO;
	
	@Autowired
	public NewsServiceImpl(NewsDAO newsDAO) {
		this.newsDAO = newsDAO;
	}

	@Override
	public List<News> getNewses(Locale locale) throws ServiceException{
		try {
			return newsDAO.getNewses(locale);
		}
		catch(DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<News> getNewses(String count, Locale locale) throws ServiceException{
		try {
			int c = Integer.parseInt(count);
			return newsDAO.getNewses(c, locale);
		}
		catch(IllegalFormatException | DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	@Override
	public News findById(String id, Locale locale) throws ServiceException{
		try {
			int newsId = Integer.parseInt(id);
			return newsDAO.findById(newsId, locale);
		}
		catch(IllegalArgumentException | DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void editNews(News news) throws ServiceException {
		try {
			newsDAO.editNews(news);
		}
		catch(DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void deleteNewsById(String id) throws ServiceException {
		try {
			int newsId = Integer.parseInt(id);
			newsDAO.deleteNewsById(newsId);
		}
		catch(DAOException | IllegalArgumentException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void deleteNews(String[] news) throws ServiceException {
		try{
			int[] someId = new int[news.length];
			for (int i = 0; i < news.length; i++) {
				someId[i] = Integer.parseInt(news[i]);
			}
			newsDAO.deleteNews(someId);
		}
		catch (DAOException | IllegalArgumentException e){
			throw new ServiceException(e);
		}
	}

	@Override
	public void addNews(News news) throws ServiceException {
		try{
			newsDAO.addNews(news);
		}
		catch (DAOException e){
			throw new ServiceException(e);
		}
	}
}

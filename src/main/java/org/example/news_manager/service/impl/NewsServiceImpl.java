package org.example.news_manager.service.impl;

import org.example.news_manager.bean.News;
import org.example.news_manager.dao.NewsDAO;
import org.example.news_manager.dao.exception.DAOException;
import org.example.news_manager.service.NewsService;
import org.example.news_manager.service.exception.ServiceException;
import org.example.news_manager.util.DateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.IllegalFormatException;
import java.util.List;
import java.util.Locale;

@Service
public class NewsServiceImpl implements NewsService {
	private final NewsDAO newsDAO;
	private final DateConverter dateConverter;
	
	@Autowired
	public NewsServiceImpl(NewsDAO newsDAO, DateConverter dateConverter) {
		this.newsDAO = newsDAO;
		this.dateConverter = dateConverter;
	}

	@Override
	@Transactional
	public List<News> getNewses(Locale locale) throws ServiceException{
		try {
			List<News> newsList = newsDAO.getNewses();
			dateConverter.getFormatDateToNewsList(newsList, locale);
			return newsList;
		}
		catch(DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	@Transactional
	public List<News> getNewses(String count, Locale locale) throws ServiceException{
		try {
			int countNews = Integer.parseInt(count);
			List<News> newsList = newsDAO.getNewses(countNews);
			dateConverter.getFormatDateToNewsList(newsList, locale);
			return newsList;
		}
		catch(IllegalFormatException | DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	@Transactional
	public News findById(String id, Locale locale) throws ServiceException{
		try {
			int newsId = Integer.parseInt(id);
			News news = newsDAO.findById(newsId);
			dateConverter.getFormatDateByNews(news, locale);
			return news;
		}
		catch(IllegalArgumentException | DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	@Transactional
	public News findById(String id) throws ServiceException{
		try {
			int newsId = Integer.parseInt(id);
			return newsDAO.findById(newsId);
		}
		catch(IllegalArgumentException | DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	@Transactional
	public void editNews(News news) throws ServiceException {
		try {
			newsDAO.editNews(news);
		}
		catch(DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	@Transactional
	public void addNews(News news) throws ServiceException {
		try{
			dateConverter.formatPublishDateToSave(news);
			newsDAO.addNews(news);
		}
		catch (DAOException e){
			throw new ServiceException(e);
		}
	}

	@Override
	@Transactional
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
	@Transactional
	public void deleteNewsList(String[] news) throws ServiceException {
		try{
			List<Integer> idList = new ArrayList<>(news.length);
			for (String newsStr: news){
				Integer x = Integer.parseInt(newsStr);
				idList.add(x);
			}
			newsDAO.deleteNewsList(idList);
		}
		catch (DAOException | IllegalArgumentException e){
			throw new ServiceException(e);
		}
	}
}

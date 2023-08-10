package org.example.news_manager.service.impl;

import org.example.news_manager.dao.NewsDAO;
import org.example.news_manager.dao.exception.DAOException;
import org.example.news_manager.dto.NewsDTO;
import org.example.news_manager.entity.NewsEntity;
import org.example.news_manager.service.NewsService;
import org.example.news_manager.service.exception.ServiceException;
import org.example.news_manager.service.util.converter.DateConverter;
import org.example.news_manager.service.util.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
	private final Mapper<NewsDTO, NewsEntity> mapper;
	
	@Autowired
	public NewsServiceImpl(NewsDAO newsDAO, 
						   @Qualifier("dateConvert") DateConverter dateConverter,
						   @Qualifier("newsMap") Mapper<NewsDTO, NewsEntity> mapper) {
		this.newsDAO = newsDAO;
		this.dateConverter = dateConverter;
		this.mapper = mapper;
	}

	@Override
	@Transactional
	public List<NewsDTO> getNewses(Locale locale) throws ServiceException{
		try {
			return getDTOConvertedNewsListInLocaleFormat(newsDAO.getNewses(), locale);
		}
		catch(DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	@Transactional
	public List<NewsDTO> getNewses(String count, Locale locale) throws ServiceException{
		try {
			int countNews = Integer.parseInt(count);
			return getDTOConvertedNewsListInLocaleFormat(newsDAO.getNewses(countNews), locale);
		}
		catch(IllegalFormatException | DAOException e) {
			throw new ServiceException(e);
		}
	}

	private List<NewsDTO> getDTOConvertedNewsListInLocaleFormat(List<NewsEntity> newsEntities, Locale locale){
		List<NewsDTO> newsDTOList = new ArrayList<>(newsEntities.size());
		for (NewsEntity entity: newsEntities){
			NewsDTO newsDTO = mapper.mapToDTO(entity);
			newsDTOList.add(newsDTO);
		}
		dateConverter.getFormatDateToNewsList(newsDTOList, locale);
		return newsDTOList;
	}

	@Override
	@Transactional
	public NewsDTO findById(String id, Locale locale) throws ServiceException{
		try {
			int newsId = Integer.parseInt(id);
			NewsEntity newsEntity = newsDAO.findById(newsId);
			NewsDTO newsDTO = mapper.mapToDTO(newsEntity);
			dateConverter.getFormatDateByNews(newsDTO, locale);
			return newsDTO;
		}
		catch(IllegalArgumentException | DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	@Transactional
	public NewsDTO findById(String id) throws ServiceException{
		try {
			int newsId = Integer.parseInt(id);
			NewsEntity newsEntity = newsDAO.findById(newsId);
			return mapper.mapToDTO(newsEntity);
		}
		catch(IllegalArgumentException | DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	@Transactional
	public void editNews(NewsDTO news) throws ServiceException {
		try {
			NewsEntity newsEntity = mapper.mapToEntity(news);
			newsDAO.editNews(newsEntity);
		}
		catch(DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	@Transactional
	public void addNews(NewsDTO news) throws ServiceException {
		try{
			dateConverter.formatPublishDateToSave(news);
			NewsEntity newsEntity = mapper.mapToEntity(news);
			newsDAO.addNews(newsEntity);
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

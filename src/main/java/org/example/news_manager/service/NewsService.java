package org.example.news_manager.service;

import org.example.news_manager.dto.NewsDTO;
import org.example.news_manager.service.exception.ServiceException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Locale;

public interface NewsService {
	List<NewsDTO> getNewses(Locale locale) throws ServiceException;
	List<NewsDTO> getNewses(String count, Locale locale) throws ServiceException;
	NewsDTO findById(String id, Locale locale) throws ServiceException;
	NewsDTO findById(String id) throws ServiceException;
	void editNews(NewsDTO news, MultipartFile image) throws ServiceException;
	void addNews(NewsDTO news, MultipartFile image) throws ServiceException;
	void deleteNewsById(String id) throws ServiceException;
	void deleteNewsList(String[] news) throws ServiceException;
}

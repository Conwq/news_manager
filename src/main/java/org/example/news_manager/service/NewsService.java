package org.example.news_manager.service;

import org.example.news_manager.bean.NewsInfoBean;
import org.example.news_manager.bean.NewsDataForNewsListBean;
import org.example.news_manager.bean.NewsDataToAddBean;
import org.example.news_manager.service.exception.ServiceException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Locale;

public interface NewsService {
	List<NewsDataForNewsListBean> getNews(Locale locale) throws ServiceException;
	List<NewsDataForNewsListBean> getNews(String count, Locale locale) throws ServiceException;
	List<NewsDataForNewsListBean> getFoundNewsByValue(String value, Locale locale) throws ServiceException;
	NewsInfoBean findById(String id, Locale locale) throws ServiceException;
	NewsInfoBean findById(String id) throws ServiceException;
	void editNews(NewsInfoBean news, MultipartFile image) throws ServiceException;
	void addNews(NewsDataToAddBean news, MultipartFile image) throws ServiceException;
	void deleteNewsById(String id) throws ServiceException;
	void deleteNewsList(String[] news) throws ServiceException;
}

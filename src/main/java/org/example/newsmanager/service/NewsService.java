package org.example.newsmanager.service;

import org.example.newsmanager.models.bean.NewsDataForNewsListBean;
import org.example.newsmanager.models.bean.NewsDataToAddBean;
import org.example.newsmanager.models.bean.NewsInfoBean;
import org.example.newsmanager.service.exception.ServiceException;
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

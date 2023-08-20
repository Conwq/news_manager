package org.example.news_manager.service;

import org.example.news_manager.bean.TagBean;
import org.example.news_manager.entity.TagEntity;
import org.example.news_manager.service.exception.ServiceException;

import java.util.List;

public interface TagService {
	List<TagBean> getAllTags() throws ServiceException;
	List<TagBean> getTagsForNewsById(String newsId) throws ServiceException;
}

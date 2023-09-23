package org.example.newsmanager.service;

import org.example.newsmanager.models.bean.TagBean;
import org.example.newsmanager.service.exception.ServiceException;

import java.util.List;

public interface TagService {
	List<TagBean> getAllTags() throws ServiceException;
	List<TagBean> getTagsForNewsById(String newsId) throws ServiceException;
}

package org.example.news_manager.service.impl;

import org.example.news_manager.models.bean.TagBean;
import org.example.news_manager.dao.TagDAO;
import org.example.news_manager.dao.exception.DAOException;
import org.example.news_manager.models.entity.TagEntity;
import org.example.news_manager.service.TagService;
import org.example.news_manager.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {
	private final TagDAO tagDAO;

	@Autowired
	public TagServiceImpl(TagDAO tagDAO){
		this.tagDAO = tagDAO;
	}

	@Override
	@Transactional
	public List<TagBean> getAllTags() throws ServiceException {
		try{
			List<TagEntity> tagsEntitieList = tagDAO.getAllTags();
			List<TagBean> tags = new ArrayList<>(tagsEntitieList.size());
			for(TagEntity tag : tagsEntitieList){
				TagBean bean = new TagBean();
				bean.setId(tag.getId());
				bean.setName(tag.getName());
				tags.add(bean);
			}
			return tags;
		}
		catch (DAOException e){
			throw new ServiceException(e);
		}
	}

	@Override
	@Transactional
	public List<TagBean> getTagsForNewsById(String newsId) throws ServiceException {
		try{
			int id = Integer.parseInt(newsId);
			List<TagBean> tags = new ArrayList<>();
			for (TagEntity entity : tagDAO.getTagsForNewsById(id)){
				TagBean tag = new TagBean();
				tag.setId(entity.getId());
				tag.setName(entity.getName());
				tags.add(tag);
			}
			return tags;
		}
		catch (DAOException | IllegalArgumentException e){
			throw new ServiceException(e);
		}
	}
}

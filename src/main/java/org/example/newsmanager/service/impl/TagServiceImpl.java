package org.example.newsmanager.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.newsmanager.models.bean.TagBean;
import org.example.newsmanager.models.entity.TagEntity;
import org.example.newsmanager.repository.TagDAO;
import org.example.newsmanager.repository.exception.DAOException;
import org.example.newsmanager.service.TagService;
import org.example.newsmanager.service.exception.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {
	private final TagDAO tagDAO;

	@Override
	@Transactional(readOnly = true)
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
	@Transactional(readOnly = true)
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

package org.example.news_manager.dao;

import org.example.news_manager.dao.exception.DAOException;
import org.example.news_manager.models.entity.TagEntity;

import java.util.List;

public interface TagDAO {
	List<TagEntity> getAllTags() throws DAOException;
	TagEntity findById(int tagId) throws DAOException;
	List<TagEntity> getTagsForNewsById(int newsId) throws DAOException;
}

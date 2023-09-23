package org.example.newsmanager.repository;

import org.example.newsmanager.repository.exception.DAOException;
import org.example.newsmanager.models.entity.TagEntity;

import java.util.List;

public interface TagDAO {
	List<TagEntity> getAllTags() throws DAOException;
	TagEntity findById(int tagId) throws DAOException;
	List<TagEntity> getTagsForNewsById(int newsId) throws DAOException;
}

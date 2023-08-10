package org.example.news_manager.service.util.mapper.impl;

import org.example.news_manager.dto.NewsDTO;
import org.example.news_manager.entity.NewsEntity;
import org.example.news_manager.service.util.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component(value = "newsMap")
public class NewsMapper implements Mapper<NewsDTO, NewsEntity> {

	@Override
	public NewsEntity mapToEntity(NewsDTO newsDTO){
		NewsEntity newsEntity = new NewsEntity();
		newsEntity.setId(newsDTO.getId());
		newsEntity.setTitle(newsDTO.getTitle());
		newsEntity.setBrief(newsDTO.getBrief());
		newsEntity.setContent(newsDTO.getContent());
		newsEntity.setImagePath(newsDTO.getImagePath());
		return newsEntity;
	}

	@Override
	public NewsDTO mapToDTO(NewsEntity newsEntity){
		NewsDTO newsDTO = new NewsDTO();
		newsDTO.setId(newsEntity.getId());
		newsDTO.setTitle(newsEntity.getTitle());
		newsDTO.setBrief(newsEntity.getBrief());
		newsDTO.setContent(newsEntity.getContent());
		newsDTO.setPublicationDate(newsEntity.getPublicationDate());
		newsDTO.setImagePath(newsEntity.getImagePath());
		return newsDTO;
	}
}

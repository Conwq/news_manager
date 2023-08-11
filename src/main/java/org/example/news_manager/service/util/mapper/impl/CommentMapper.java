package org.example.news_manager.service.util.mapper.impl;

import org.example.news_manager.dto.CommentDTO;
import org.example.news_manager.entity.CommentEntity;
import org.example.news_manager.service.util.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component(value = "commentMap")
public class CommentMapper implements Mapper<CommentDTO, CommentEntity>{

	@Override
	public CommentEntity mapToEntity(CommentDTO obj) {
		CommentEntity commentEntity = new CommentEntity();
		commentEntity.setId(obj.getId());
		commentEntity.setText(obj.getText());
		commentEntity.setPublicationDate(obj.getPublicationDate());
		
		return commentEntity;
	}

	@Override
	public CommentDTO mapToDTO(CommentEntity entity) {
		CommentDTO commentDTO = new CommentDTO();
		commentDTO.setId(entity.getId());
		commentDTO.setText(entity.getText());
		commentDTO.setPublicationDate(entity.getPublicationDate());
		commentDTO.setUsername(entity.getUserEntity().getLogin());
		
		return commentDTO;
	}
	
	
}

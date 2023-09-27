package org.example.newsmanager.service.util.mapper.extend;

import org.example.newsmanager.models.bean.CommentResponse;
import org.example.newsmanager.models.entity.CommentEntity;
import org.example.newsmanager.service.util.converter.DateConverter;
import org.example.newsmanager.service.util.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;

@Component
public class CommentMapper extends Mapper<CommentEntity, CommentResponse> {
	private final DateConverter dateConverter;

	@Autowired
	public CommentMapper(DateConverter dateConverter) {
		this.dateConverter = dateConverter;
	}

	@Override
	public CommentResponse mapToDto(CommentEntity comment, @Nullable Locale locale) {
		CommentResponse commentResponse = CommentResponse.builder()
				.id(comment.getId())
				.text(comment.getText())
				.username(comment.getUserEntity().getLogin())
				.build();
		if (locale != null) {
			String formatPublicationDate = dateConverter.getFormatDateByComment(comment.getPublicationDate(), locale);
			commentResponse.setPublicationDate(formatPublicationDate);
		}
		return commentResponse;
	}

	@Override
	public List<CommentResponse> mapListToDto(List<CommentEntity> commentsList, Locale locale) {
		return commentsList.stream()
				.map(comment -> mapToDto(comment, locale))
				.toList();
	}

	@Override
	public void mapToEntity() {

	}
}

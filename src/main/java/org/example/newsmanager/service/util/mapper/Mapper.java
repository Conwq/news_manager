package org.example.newsmanager.service.util.mapper;

import org.example.newsmanager.models.bean.CommentResponse;
import org.example.newsmanager.models.entity.CommentEntity;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Locale;

public abstract class Mapper<T, E> {
	abstract public E mapToDto(T t, @Nullable Locale locale);
	abstract public List<E> mapListToDto(List<T> t, @Nullable Locale locale);
	abstract public void mapToEntity();
}

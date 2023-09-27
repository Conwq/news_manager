package org.example.newsmanager.service.util.mapper;

import java.util.Locale;

public abstract class Mapper<T, E> {
	public abstract E mapToDto(T t, Locale locale);
	public abstract void mapToEntity();
}

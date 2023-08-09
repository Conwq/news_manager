package org.example.news_manager.service.util.mapper;

import org.springframework.stereotype.Component;

public interface Mapper <T, R>{
	R mapToEntity(T obj);
	T mapToDTO(R obj);
}

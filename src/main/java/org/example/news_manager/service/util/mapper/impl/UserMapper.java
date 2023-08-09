package org.example.news_manager.service.util.mapper.impl;

import org.example.news_manager.dto.Role;
import org.example.news_manager.dto.UserDTO;
import org.example.news_manager.entity.UserEntity;
import org.example.news_manager.service.util.mapper.Mapper;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component(value = "userMapper")
public class UserMapper implements Mapper<UserDTO, UserEntity> {
	
	@Override
	public UserEntity mapToEntity(UserDTO userBean) {
		UserEntity userEntity = new UserEntity();
		userEntity.setEmail(userBean.getEmail());
		userEntity.setLogin(userBean.getLogin());
		userEntity.setPassword(userBean.getPassword());
		return userEntity;
	}

	@Override
	public UserDTO mapToDTO(UserEntity userEntity) {
		UserDTO userBean = new UserDTO();
		userBean.setId(userEntity.getId());
		userBean.setEmail(userEntity.getEmail());
		userBean.setLogin(userEntity.getLogin());
		userBean.setPassword(userEntity.getPassword());

		String country = userEntity.getLocaleEntity().getCountry();
		String language = userEntity.getLocaleEntity().getLanguage();
		userBean.setLocale(new Locale(language, country));

		String roleName = userEntity.getRoleEntity().getRoleName().toUpperCase();
		userBean.setRole(Role.valueOf(roleName));

		return userBean;
	}
}

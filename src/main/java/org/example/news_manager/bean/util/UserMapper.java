package org.example.news_manager.bean.util;

import java.util.Locale;

import org.example.news_manager.bean.Role;
import org.example.news_manager.bean.UserBean;
import org.example.news_manager.entity.LocaleEntity;
import org.example.news_manager.entity.RoleEntity;
import org.example.news_manager.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
	
	public UserMapper() {
	}
	
	public UserBean convertToUserBean(UserEntity userEntity) {
		UserBean userBean = new UserBean();
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
	
	public UserEntity convertToUserEntity(UserBean userBean) {
		UserEntity userEntity = new UserEntity();
		LocaleEntity localeEntity = new LocaleEntity();
		
		userEntity.setEmail(userBean.getEmail());
		userEntity.setLogin(userBean.getLogin());
		userEntity.setPassword(userBean.getPassword());
		
		int localeId = 2;
		if(userBean.getLocale().toString().equals("ru_RU")) {
			localeId = 1;
		}
		localeEntity.setId(localeId);
		userEntity.setLocaleEntity(localeEntity);
		
		return userEntity;
	}
}

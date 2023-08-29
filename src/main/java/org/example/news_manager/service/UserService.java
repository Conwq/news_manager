package org.example.news_manager.service;

import org.example.news_manager.models.bean.UserRegistrationDataBean;
import org.example.news_manager.service.exception.ServiceException;

public interface UserService {
	void registration(UserRegistrationDataBean user) throws ServiceException;
}

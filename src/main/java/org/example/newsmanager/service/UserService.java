package org.example.newsmanager.service;

import org.example.newsmanager.models.bean.UserRegistrationDataBean;
import org.example.newsmanager.service.exception.ServiceException;

public interface UserService {
	void registration(UserRegistrationDataBean user) throws ServiceException;
}

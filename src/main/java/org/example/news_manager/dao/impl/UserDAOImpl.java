package org.example.news_manager.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;

import org.example.news_manager.dao.UserDAO;
import org.example.news_manager.dao.exception.DAOException;
import org.example.news_manager.entity.LocaleEntity;
import org.example.news_manager.entity.RoleEntity;
import org.example.news_manager.entity.UserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAO{
	private SessionFactory sessionFactory;
	
	public UserDAOImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public UserEntity signIn(String login, String password) throws DAOException {
		Session session = sessionFactory.getCurrentSession();
		Query<UserEntity> query = session.createQuery("FROM UserEntity WHERE login = :loginUser", UserEntity.class);
		query.setParameter("loginUser", login);
		UserEntity userEntity = null;
		try {
			userEntity = query.getSingleResult();
		}
		catch (NoResultException e) {
			throw new DAOException("This user was not found");
		}
		if(!BCrypt.checkpw(password, userEntity.getPassword())) {
			throw new DAOException("Incorrect password");
		}
		return userEntity;
	}

	@Override
	public void registration(UserEntity userEntity) throws DAOException{
		Session session = sessionFactory.getCurrentSession();
		userEntity.setRoleEntity(session.get(RoleEntity.class, 1));
		userEntity.setLocaleEntity(session.get(LocaleEntity.class, userEntity.getLocaleEntity().getId()));
		session.save(userEntity);
	}
}

package org.example.news_manager.dao.impl;

import org.example.news_manager.dao.UserDAO;
import org.example.news_manager.dao.exception.DAOException;
import org.example.news_manager.entity.AuthorityEntity;
import org.example.news_manager.entity.LocaleEntity;
import org.example.news_manager.entity.RoleEntity;
import org.example.news_manager.entity.UserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;

@Repository
public class UserDAOImpl implements UserDAO{
	private final SessionFactory sessionFactory;

	@Autowired
	public UserDAOImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public UserEntity signIn(String login, String password) throws DAOException {
		Session session = sessionFactory.getCurrentSession();
		Query<UserEntity> query = session.createQuery("FROM UserEntity WHERE login = :loginUser", UserEntity.class);
		query.setParameter("loginUser", login);
		UserEntity userEntity;
		try {
			userEntity = query.getSingleResult();
			if(!BCrypt.checkpw(password, userEntity.getPassword())) {
				throw new DAOException("Incorrect password");
			}
			return userEntity;
		}
		catch (NoResultException e) {
			throw new DAOException("This user was not found");
		}
		catch (Exception e){
			throw new DAOException(e);
		}
	}

	@Override
	public void registration(UserEntity userEntity, int localeId) throws DAOException{
		try {
			Session session = sessionFactory.getCurrentSession();

			LocaleEntity localeEntity = session.get(LocaleEntity.class, localeId);
			AuthorityEntity authorityEntity = session.get(AuthorityEntity.class, 1);
			userEntity.setLocaleEntity(localeEntity);
			userEntity.setAuthority(authorityEntity);

			localeEntity.getUsersEntity().add(userEntity);
			authorityEntity.getUserEntity().add(userEntity);

			session.save(userEntity);
		}
		catch (Exception e){
			throw new DAOException(e);
		}
	}

	@Override
	public UserEntity findUserByUsername(String login){
		Session session = sessionFactory.getCurrentSession();
		Query<UserEntity> query = session.createQuery("FROM UserEntity WHERE login = :loginUser", UserEntity.class);
		query.setParameter("loginUser", login);
		return query.getSingleResult();
	}
}

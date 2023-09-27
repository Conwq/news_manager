package org.example.newsmanager.repository.impl;

import lombok.RequiredArgsConstructor;
import org.example.newsmanager.models.entity.AuthorityEntity;
import org.example.newsmanager.models.entity.LocaleEntity;
import org.example.newsmanager.models.entity.UserEntity;
import org.example.newsmanager.repository.UserDAO;
import org.example.newsmanager.repository.exception.DAOException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserDAOImpl implements UserDAO {
	private final SessionFactory sessionFactory;

	@Override
	public void registration(UserEntity userEntity, int localeId) throws DAOException {
		try {
			Session session = sessionFactory.getCurrentSession();

			LocaleEntity localeEntity = session.get(LocaleEntity.class, localeId);
			AuthorityEntity authorityEntity = session.get(AuthorityEntity.class, 1);
			userEntity.setLocaleEntity(localeEntity);
			userEntity.setAuthority(authorityEntity);

			localeEntity.getUsersEntity().add(userEntity);
			authorityEntity.getUserEntity().add(userEntity);

			session.save(userEntity);
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	@Override
	public Optional<UserEntity> findUserByUsername(String login) {
		Session session = sessionFactory.getCurrentSession();
		Query<UserEntity> query = session.createQuery("FROM UserEntity WHERE login = :loginUser", UserEntity.class);
		query.setParameter("loginUser", login);
		return Optional.of(query.getSingleResult());
	}
}

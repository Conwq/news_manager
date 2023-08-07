package org.example.news_manager.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

import org.example.news_manager.dao.UserDAO;
import org.example.news_manager.dao.exception.DAOException;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Repository;

import org.example.news_manager.bean.Role;
import org.example.news_manager.bean.User;

@Repository
public class UserDAOImpl implements UserDAO{
	private Connection connection;
	
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/news_manager", "root", "1");
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static final String SQL_SIGN_IN = "SELECT * FROM users " +
			"JOIN roles ON users.role_id = roles.role_id " +
			"JOIN locales ON users.user_id = locales.user_id " +
			"WHERE login=?";
	@Override
	public User signIn(String login, String password) throws DAOException{
		try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_SIGN_IN)){
			preparedStatement.setString(1, login);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(!resultSet.next()) {
				throw new DAOException("User with this login not find");
			}
			if(!BCrypt.checkpw(password, resultSet.getString("password"))) {
				throw new DAOException("Not find this password");
			}
			User user = new User();
			user.setId(resultSet.getInt("user_id"));
			user.setLogin(resultSet.getString("login"));
			user.setEmail(resultSet.getString("email"));
			user.setRole(Role.valueOf(resultSet.getString("role_name").toUpperCase()));
			String country = resultSet.getString("country");
			String language = resultSet.getString("language");
			user.setLocale(new Locale(country, language));
			return user;
		}
		catch(SQLException e) {
			throw new DAOException(e);
		}
	}

	private static final String SQL_REGISTRATION_USER = "INSERT INTO users (email, login, password, role_id) VALUE (?, ?, ?, 1)";
	private static final String SQL_ADD_LOCALE = "INSERT INTO locales (user_id, country, language) VALUE (?,?,?)";
	@Override
	public void registration(User user) throws DAOException{
		try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_REGISTRATION_USER, PreparedStatement.RETURN_GENERATED_KEYS)){
			preparedStatement.setString(1, user.getEmail());
			preparedStatement.setString(2, user.getLogin());
			preparedStatement.setString(3, user.getPassword());
			preparedStatement.executeUpdate();

			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			if (!resultSet.next()){
				throw new DAOException("Error with registration");
			}
			int id = resultSet.getInt(1);
			PreparedStatement preparedStatement1 = connection.prepareStatement(SQL_ADD_LOCALE);
			preparedStatement1.setInt(1, id);
			preparedStatement1.setString(2, user.getLocale().getCountry());
			preparedStatement1.setString(3, user.getLocale().getLanguage());
			preparedStatement1.executeUpdate();
		}
		catch(SQLException e) {
			throw new DAOException(e);
		}
	}
}

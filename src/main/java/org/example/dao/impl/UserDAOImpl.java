package org.example.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.example.dao.UserDAO;
import org.example.dao.exception.DAOException;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Repository;

import org.example.entity.Role;
import org.example.entity.User;

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
	
	private static final String SQL_SIGN_IN = "SELECT * FROM users JOIN roles ON users.role_id = roles.role_id WHERE login=?";
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
			return user;
		}
		catch(SQLException e) {
			throw new DAOException(e);
		}
	}

	private static final String SQL_REGISTRATION_USER = "INSERT INTO users (email, login, password, role_id) VALUE (?, ?, ?, 1)";
	@Override
	public void registration(User user) throws DAOException{
		try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_REGISTRATION_USER)){
			preparedStatement.setString(1, user.getEmail());
			preparedStatement.setString(2, user.getLogin());
			preparedStatement.setString(3, user.getPassword());
			preparedStatement.executeUpdate();
		}
		catch(SQLException e) {
			throw new DAOException(e);
		}
	}
}

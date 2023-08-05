package org.example.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.example.dao.NewsDAO;
import org.example.dao.exception.DAOException;
import org.springframework.stereotype.Repository;

import org.example.entity.News;

@Repository
public class NewsDAOImpl implements NewsDAO{
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
	
	private static final String SQL_GET_ALL_NEWSES = "SELECT * FROM news ORDER BY publication_date DESC";
	@Override
	public List<News> getNewses() throws DAOException{
		ResultSet resultSet = null;
		try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_ALL_NEWSES)){
			List<News> newses = new ArrayList<>();
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				News news = new News();
				news.setId(resultSet.getInt("news_id"));
				news.setTitle(resultSet.getString("title"));
				news.setBrief(resultSet.getString("brief"));
				news.setContent(resultSet.getString("content"));
				news.setPublicationDate(resultSet.getString("publication_date"));
				newses.add(news);
			}
			return newses;
		}
		catch(SQLException e) {
			throw new DAOException();
		}
		finally {
			if(resultSet != null) {
				try {
					resultSet.close();
				} 
				catch (SQLException e) {
					throw new DAOException();
				}
			}
		}
	}
	
	private static final String SQL_GET_COUNT_NEWS = "SELECT * FROM news ORDER BY publication_date DESC LIMIT ?";
	@Override
	public List<News> getNewses(int count) throws DAOException{
		ResultSet resultSet = null;
		try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_COUNT_NEWS)){
			List<News> newses = new ArrayList<>();
			preparedStatement.setInt(1, count);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				News news = new News();
				news.setId(resultSet.getInt("news_id"));
				news.setTitle(resultSet.getString("title"));
				news.setBrief(resultSet.getString("brief"));
				news.setContent(resultSet.getString("content"));
				news.setPublicationDate(resultSet.getString("publication_date"));
				newses.add(news);
			}
			return newses;
		}
		catch(SQLException e) {
			throw new DAOException(e);
		}
		finally {
			if(resultSet != null) {
				try {
					resultSet.close();
				} 
				catch (SQLException e) {
					throw new DAOException();
				}
			}
		}
	}
	
	private static final String SQL_GET_NEWS_BY_ID = "SELECT * FROM news WHERE news_id = ?";
	@Override
	public News findById(int id) throws DAOException {
		try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_NEWS_BY_ID)) {
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(!resultSet.next()) {
				throw new DAOException("News with current id not found");
			}
			News news = new News();
			news.setId(resultSet.getInt("news_id"));
			news.setTitle(resultSet.getString("title"));
			news.setBrief(resultSet.getString("brief"));
			news.setContent(resultSet.getString("content"));
			news.setPublicationDate(resultSet.getString("publication_date"));
			return news;
		}
		catch(SQLException e) {
			throw new DAOException(e);
		}
	}
	
	private static final String SQL_EDIT_NEWS = "UPDATE news SET title=?, brief=?, content=? WHERE news_id=?";
	@Override
	public void editNews(News news) throws DAOException {
		try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_EDIT_NEWS)){
			preparedStatement.setString(1, news.getTitle());
			preparedStatement.setString(2, news.getBrief());
			preparedStatement.setString(3, news.getContent());
			preparedStatement.setInt(4, news.getId());
			
			preparedStatement.executeUpdate();
		}
		catch(SQLException e) {
			throw new DAOException(e);
		}
	}
	
	private static final String SQL_DELETE_NEWS = "DELETE FROM news WHERE news_id=?";
	@Override
	public void deleteNewsById(int newsId) throws DAOException {
		try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_NEWS)){
			preparedStatement.setInt(1, newsId);
			
			preparedStatement.executeUpdate();
		}
		catch(SQLException e) {
			throw new DAOException(e);
		}
	}

	private static final String SQL_DELETE_SOME_NEWS = "DELETE FROM news WHERE news_id IN (%s)";
	@Override
	public void deleteNews(int[] someId) throws DAOException {
		String parameter = preparedParameter(someId);
		try (PreparedStatement preparedStatement = connection.prepareStatement(String.format(SQL_DELETE_SOME_NEWS, parameter))){
			for (int i = 0; i < someId.length; i++) {
				preparedStatement.setInt(i + 1, someId[i]);
			}
			preparedStatement.executeUpdate();
		}
		catch (SQLException e){
			throw new DAOException(e);
		}
	}

	private String preparedParameter(int[] someId){
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < someId.length; i++) {
			if (someId.length - 1 == i){
				stringBuilder.append("?");
				break;
			}
			stringBuilder.append("?,");
		}
		return stringBuilder.toString();
	}

	private static final String SQL_ADD_NEWS = "INSERT INTO news (title, brief, content) VALUE (?,?,?)";
	@Override
	public void addNews(News news) throws DAOException {
		try(PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_NEWS)) {
			preparedStatement.setString(1, news.getTitle());
			preparedStatement.setString(2, news.getBrief());
			preparedStatement.setString(3, news.getContent());

			preparedStatement.executeUpdate();
		}
		catch (SQLException e){
			throw new DAOException(e);
		}
	}
}

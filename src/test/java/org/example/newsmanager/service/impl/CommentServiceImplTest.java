package org.example.newsmanager.service.impl;

import org.assertj.core.api.AssertionsForClassTypes;
import org.example.newsmanager.models.bean.CommentResponse;
import org.example.newsmanager.models.entity.CommentEntity;
import org.example.newsmanager.models.entity.NewsEntity;
import org.example.newsmanager.models.entity.UserEntity;
import org.example.newsmanager.repository.CommentDAO;
import org.example.newsmanager.repository.NewsDAO;
import org.example.newsmanager.repository.UserDAO;
import org.example.newsmanager.repository.exception.DAOException;
import org.example.newsmanager.service.CommentService;
import org.example.newsmanager.service.exception.ServiceException;
import org.example.newsmanager.service.util.mapper.Mapper;
import org.example.newsmanager.service.util.mapper.extend.CommentMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class CommentServiceImplTest {
	private final CommentDAO commentDAO = Mockito.mock(CommentDAO.class);
	private final NewsDAO newsDAO = Mockito.mock(NewsDAO.class);
	private final UserDAO userDAO = Mockito.mock(UserDAO.class);
	private final Mapper<CommentEntity, CommentResponse> mapper = Mockito.mock(CommentMapper.class);
	private CommentService commentService;
	private CommentEntity commentEntity;
	private CommentResponse commentResponse;
	private List<CommentEntity> commentEntities;
	private List<CommentResponse> commentResponses;

	@BeforeEach
	void setUp() {
		commentService = new CommentServiceImpl(commentDAO, userDAO, newsDAO, mapper);

		commentEntity = new CommentEntity(1, "Text for current comment.", Instant.now().toString(), new UserEntity(), new NewsEntity());
		commentResponse = new CommentResponse(1, "Text for current comment.", "Tom", Instant.now().toString());

		commentEntities = new ArrayList<>(2) {{
			add(new CommentEntity(1, "Text for commentOne.", null, null, null));
			add(new CommentEntity(2, "Text for commentTwo.", null, null, null));
		}};

		commentResponses = new ArrayList<>() {{
			add(new CommentResponse(1, "Text for commentOne.", null, null));
			add(new CommentResponse(2, "Text for commentTwo.", null, null));
		}};
	}

	@AfterEach
	void tearDown() {
	}

	@Test
	public void shouldGetCommentById() throws DAOException, ServiceException {
		UserEntity userEntity = new UserEntity();
		userEntity.setLogin("Tom");
		commentEntity.setUserEntity(userEntity);

		Mockito.when(commentDAO.getCommentById(Mockito.any(Integer.class))).thenReturn(Optional.of(commentEntity));
		Mockito.when(mapper.mapToDto(commentEntity, null)).thenReturn(commentResponse);

		CommentResponse expected = commentService.getCommentById("1");

		AssertionsForClassTypes.assertThat(commentResponse.getId()).isEqualTo(expected.getId());
		AssertionsForClassTypes.assertThat(commentResponse.getUsername()).isEqualTo(expected.getUsername());
	}

	@Test
	@DisplayName("Should throw an exception when trying to take a non-existent id")
	public void shouldThrowExceptionWithNonExistId() throws DAOException {
		final int commentId = 131;

		Mockito.when(commentDAO.getCommentById(commentId))
				.thenReturn(Optional.empty());

		AssertionsForClassTypes.assertThatThrownBy(() -> {
					commentService.getCommentById(String.valueOf(commentId));
				}).isInstanceOf(ServiceException.class)
				.hasMessage("Comment with id `" + commentId + "` not found.");
	}

	@Test
	public void shouldDeleteCommentById() throws DAOException, ServiceException {
		final int id = 1;

		Mockito.when(commentDAO.getCommentById(id)).thenReturn(Optional.of(commentEntity));
		commentService.deleteCommentById(String.valueOf(id));
		Mockito.verify(commentDAO, Mockito.times(1)).deleteCommentById(id);
	}

	@Test
	public void shouldThrowExceptionWhenDeletingNonExistentId() throws DAOException {
		Mockito.when(commentDAO.getCommentById(100)).thenReturn(Optional.empty());

		AssertionsForClassTypes.assertThatThrownBy(() -> {
					commentService.deleteCommentById("100");
				}).isInstanceOf(ServiceException.class)
				.hasMessage("Comment with id `100` not found.");
	}

	@Test
	public void shouldReturnAllCommentsByNewsId() throws DAOException, ServiceException {
		final int newsId = 1;

		Mockito.when(commentDAO.getAllCommentsByNewsId(newsId))
				.thenReturn(commentEntities);

		Mockito.when(mapper.mapListToDto(commentEntities, null))
				.thenReturn(commentResponses);

		List<CommentResponse> expected = commentService.getAllCommentsByNewsId(String.valueOf(newsId), null);

		AssertionsForClassTypes.assertThat(commentResponses.get(0).getId()).isEqualTo(expected.get(0).getId());
		AssertionsForClassTypes.assertThat(commentResponses.get(0).getText()).isEqualTo(expected.get(0).getText());
		AssertionsForClassTypes.assertThat(commentResponses.get(1).getId()).isEqualTo(expected.get(1).getId());
		AssertionsForClassTypes.assertThat(commentResponses.get(1).getText()).isEqualTo(expected.get(1).getText());
	}

	@Test
	public void shouldSaveBook() throws DAOException, ServiceException {
		final String text = "Comment text.";
		final String username = "user813711";
		final String newsId = "1";

		NewsEntity news = new NewsEntity(1, "Title", "Brief", "Content", Instant.now().toString(),
				null, new ArrayList<>(), null);
		UserEntity user = new UserEntity(11, "user@mail.com", username, "1",
				null, new ArrayList<>(), null);

		Mockito.when(newsDAO.findById(1)).thenReturn(Optional.of(news));
		Mockito.when(userDAO.findUserByUsername(username)).thenReturn(Optional.of(user));

		commentService.saveComment(text, username, newsId);

		Mockito.verify(newsDAO, Mockito.times(1)).findById(1);
		Mockito.verify(userDAO, Mockito.times(1)).findUserByUsername(username);
		Mockito.verify(commentDAO, Mockito.times(1)).saveComment(Mockito.any(CommentEntity.class));
	}

	@Test
	public void shouldThrowExceptionIfInputDataNull() {
		String[] texts = {null, null, "1", "1", "1", null};
		String[] usernames = {null, "1", null, null, null, "1"};
		String[] newsIds = {"1", null, null, "1", "1", "1"};

		for (int i = 0; i < texts.length; i++) {
			int index = i;
			AssertionsForClassTypes.assertThatThrownBy(() -> {
						commentService.saveComment(texts[index], usernames[index], newsIds[index]);
					}).isInstanceOf(ServiceException.class)
					.hasMessage("Data cannot be empty.");
		}
	}

	@Test
	public void shouldThrowExceptionIfReturnOptionalEmpty() throws DAOException {
		final String text = "Text for comment.";
		final String username = "Not_exist_user";
		final int newsId = 1121;

		Mockito.when(userDAO.findUserByUsername(username)).thenReturn(Optional.empty());
		Mockito.when(newsDAO.findById(newsId)).thenReturn(Optional.empty());

		AssertionsForClassTypes.assertThatThrownBy(() -> {
			commentService.saveComment(text, username, String.valueOf(newsId));
		});

		Mockito.when(userDAO.findUserByUsername(username)).thenReturn(Optional.of(Mockito.mock(UserEntity.class)));
		Mockito.when(newsDAO.findById(newsId)).thenReturn(Optional.empty());

		AssertionsForClassTypes.assertThatThrownBy(() -> {
			commentService.saveComment(text, username, String.valueOf(newsId));
		});

		Mockito.when(userDAO.findUserByUsername(username)).thenReturn(Optional.empty());
		Mockito.when(newsDAO.findById(newsId)).thenReturn(Optional.of(Mockito.mock(NewsEntity.class)));

		AssertionsForClassTypes.assertThatThrownBy(() -> {
			commentService.saveComment(text, username, String.valueOf(newsId));
		});
	}

	@Test
	public void shouldEditComment() throws DAOException, ServiceException {
		final int commentId = 1;
		final String text = "Text for comment";
		CommentEntity comment = new CommentEntity();

		Mockito.when(commentDAO.getCommentById(1)).thenReturn(Optional.of(comment));
		commentService.editCommentById(String.valueOf(commentId), text);
		Mockito.verify(commentDAO, Mockito.times(1)).getCommentById(commentId);
		Mockito.verify(commentDAO, Mockito.times(1)).editCommentById(commentId, text);
	}

	@Test
	public void shouldThrowExceptionIfCommentNotFound() throws DAOException {
		final int commentId = 1231;

		Mockito.when(commentDAO.getCommentById(commentId)).thenReturn(Optional.empty());
		AssertionsForClassTypes.assertThatThrownBy(() -> {
					commentService.editCommentById(String.valueOf(commentId), null);
				}).isInstanceOf(ServiceException.class)
				.hasMessage("Comment with id `" + commentId + "` not found.");
	}
}
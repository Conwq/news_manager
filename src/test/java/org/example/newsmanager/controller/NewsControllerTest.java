package org.example.newsmanager.controller;

import org.example.newsmanager.service.CommentService;
import org.example.newsmanager.service.NewsService;
import org.example.newsmanager.service.TagService;
import org.example.newsmanager.service.exception.ServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class NewsControllerTest {
	private final NewsService newsService = Mockito.mock(NewsService.class);
	private final CommentService commentService = Mockito.mock(CommentService.class);
	private final TagService tagService = Mockito.mock(TagService.class);
	private MockMvc mockMvc;

	@BeforeEach
	void setUp() {
		NewsController newsController = new NewsController(newsService, commentService, tagService);
		mockMvc = MockMvcBuilders.standaloneSetup(newsController).build();
	}

	@Test
	public void shouldSaveComment() throws Exception {
		String text = "Text for comment.";
		String newsId = "1";
		String username = "User123";

		MockHttpServletRequestBuilder requestBuilders = MockMvcRequestBuilders.post("/news/doAddComment")
				.param("text", text)
				.param("newsId", newsId)
				.param("username", username);

		mockMvc.perform(requestBuilders)
				.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
				.andExpect(MockMvcResultMatchers.redirectedUrl("/news/goToViewNews?id=" + newsId));

		Mockito.verify(commentService, Mockito.times(1)).saveComment(text, username, newsId);
	}

	@Test
	public void shouldRedirectIfThrowExceptionSaveComment() throws Exception {
		String text = "Text for comment.";
		String newsId = "1";
		String username = "User123";

		RequestBuilder requestBuilders = MockMvcRequestBuilders.post("/news/doAddComment")
				.contentType(MediaType.APPLICATION_JSON)
				.param("text", text)
				.param("newsId", newsId)
				.param("username", username);

		Mockito.doThrow(new ServiceException()).when(commentService).saveComment(text, username, newsId);

		mockMvc.perform(requestBuilders)
				.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
				.andExpect(MockMvcResultMatchers.redirectedUrl("/news/errorPage"));

		Mockito.verify(commentService, Mockito.times(1)).saveComment(text, username, newsId);
	}

	@Test
	public void shouldDeleteComment() throws Exception {
		String commentId = "1";
		String newsId = "1";

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/news/doDeleteComment")
				.param("commentId", commentId)
				.param("newsId", newsId);

		mockMvc.perform(requestBuilder)
				.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
				.andExpect(MockMvcResultMatchers.redirectedUrl("/news/goToViewNews?id=" + newsId));

		Mockito.verify(commentService, Mockito.times(1)).deleteCommentById(commentId);
	}

	@Test
	public void shouldThrowExceptionWhenDeleteComment() throws Exception {
		String commentId = "1";
		String newsId = "1";

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/news/doDeleteComment")
				.param("commentId", commentId)
				.param("newsId", newsId);

		Mockito.doThrow(new ServiceException()).when(commentService).deleteCommentById(commentId);

		mockMvc.perform(requestBuilder)
				.andExpect(MockMvcResultMatchers.status().is3xxRedirection())
				.andExpect(MockMvcResultMatchers.redirectedUrl("/news/errorPage"));

		Mockito.verify(commentService, Mockito.times(1)).deleteCommentById(commentId);
	}
}
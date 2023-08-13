package org.example.news_manager.controller;

import org.example.news_manager.bean.*;
import org.example.news_manager.service.CommentService;
import org.example.news_manager.service.NewsService;
import org.example.news_manager.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/news")
public class FrontController {
	private final NewsService newsService;
	private final CommentService commentService;

	@Autowired
	public FrontController(NewsService newsService,
						   CommentService commentService) {
		this.newsService = newsService;
		this.commentService = commentService;
	}

	
	/***********
	 ***********
	 ****NEWS***
	 *********** 
	 ***********/
	
	@GetMapping
	public String goToBasePage(HttpServletRequest request,
							   Model model) {
		try {
			String localeParameter = (String) request.getSession().getAttribute("localization");
			Locale locale = localeParameter == null ? LocaleContextHolder.getLocale() : new Locale(localeParameter);

			List<NewsDataForNewsListBean> news = newsService.getNews("5", locale);
			model.addAttribute("news", news);
			model.addAttribute("action", "newsList");
			return "baseLayout/baseLayout";
		} 
		catch (ServiceException e) {
			return "redirect:/news/errorPage";
		}
	}

	@GetMapping("/goToNewsList")
	public String goToNewsList(@SessionAttribute("locale") Locale locale,
							   Model model) {
		try {
			List<NewsDataForNewsListBean> news = newsService.getNews(locale);
			model.addAttribute("news", news);
			model.addAttribute("action", "newsList");
			return "baseLayout/baseLayout";
		} 
		catch (ServiceException e) {
			return "redirect:/news/errorPage";
		}
	}

	@GetMapping("/getSearchResult")
	public String getFoundNewsByValue(@RequestParam("value") String value,
									  @SessionAttribute("locale") Locale locale,
									  Model model){
		try{
			List<NewsDataForNewsListBean> news =  newsService.getFoundNewsByValue(value, locale);
			model.addAttribute("news", news);
			model.addAttribute("action", "newsList");
			return "baseLayout/baseLayout";
		}
		catch (ServiceException e){
			e.printStackTrace();
		return "redirect:/news/errorPage";
		}
	}

	@GetMapping("/goToAddNewsPage")
	public String showAddNewsPage(Model model) {
		NewsDataToAddBean news = new NewsDataToAddBean();
		model.addAttribute("news", news);
		model.addAttribute("action", "addNewsPage");
		return "baseLayout/baseLayout";
	}

	@PostMapping("/doAddNews")
	public String addNews(@ModelAttribute("news") NewsDataToAddBean news,
						  @RequestParam("image") MultipartFile image) {
		try {
			newsService.addNews(news, image);
			return "redirect:/news/goToNewsList";
		}
		catch (ServiceException e){
			return "redirect:/news/errorPage";
		}
	}

	@GetMapping("/goToViewNews")
	public String showNews(@RequestParam("id") String id,
						   @SessionAttribute("locale") Locale locale,
						   Model model) {
		try {
			NewsInfoBean news = newsService.findById(id, locale);
			List<CommentInfoBean> comments = commentService.getCommentsFromNewsById(id, locale);
			
			model.addAttribute("comments", comments);
			model.addAttribute("news", news);
			model.addAttribute("action", "viewNews");
			
			return "baseLayout/baseLayout";
		} 
		catch (ServiceException e) {
			return "redirect:/news/errorPage";
		}
	}

	@GetMapping("/goToEditNews")
	public String showEditNewsPage(@RequestParam("id") String id,
								   Model model) {
		try {
			NewsInfoBean news = newsService.findById(id);
			model.addAttribute("news", news);
			model.addAttribute("action", "editNews");
			return "baseLayout/baseLayout";
		} 
		catch (ServiceException e) {
			return "redirect:/news/errorPage";
		}
	}

	@PostMapping("/doEditNews")
	public String doEditNews(@ModelAttribute("news") NewsInfoBean news,
							 @RequestParam("image") MultipartFile image) {
		try {
			newsService.editNews(news, image);
			return "redirect:/news/goToViewNews?id=" + news.getId();
		} 
		catch (ServiceException e) {
			return "redirect:/news/errorPage";
		}
	}

	@RequestMapping("/doDeleteNews")
	public String doDeleteNews(@RequestParam("id") String id) {
		try {
			newsService.deleteNewsById(id);
			return "redirect:/news/goToNewsList";
		} 
		catch (ServiceException e) {
			return "redirect:/news/errorPage";
		}
	}

	@RequestMapping("/doDeleteSomeNews")
	public String deleteNewsList(@RequestParam("news") String[] news) {
		try {
			newsService.deleteNewsList(news);
			return "redirect:/news/goToNewsList";
		} 
		catch (ServiceException e) {
			return "redirect:/news/errorPage";
		}
	}

	@GetMapping("/changeLocale")
	public String changeLocale(HttpServletRequest request) {
		request.getSession().setAttribute("localization", request.getParameter("localization"));
		request.getSession().setAttribute("locale", new Locale(request.getParameter("localization")));
		return "redirect:" + getRequestURL(request);
	}

	private String getRequestURL(HttpServletRequest request){
		try {
			URI uri = new URI(request.getHeader("referer"));
			String path = uri.getPath();
			if (uri.getQuery() != null) {
				path += "?" + uri.getQuery();
			}
			return path;
		}
		catch (URISyntaxException e){
			throw new RuntimeException(e);
		}
	}
	
	/***********
	 ***********
	 **COMMENTS*
	 *********** 
	 ***********/
	
	@PostMapping("/doAddComment")
	public String addComment(@RequestParam("text") String text,
							 @RequestParam("newsId") String newsId,
							 @SessionAttribute("user") UserInfoBean user) {
		try {
			commentService.saveComment(text, user.getId(), newsId);
			return "redirect:/news/goToViewNews?id=" + newsId;
		}
		catch(ServiceException e) {
			return "redirect:/news/errorPage";
		}
	}
	
	@PostMapping("/doDeleteComment")
	public String deleteComment(@RequestParam("commentId") String commentId,
								@RequestParam("newsId") String newsId) {
		try {
			commentService.deleteCommentById(commentId);
			return "redirect:/news/goToViewNews?id=" + newsId;
		}
		catch(ServiceException e) {
			return "redirect:/news/errorPage";
		}
	}
	
	@RequestMapping("/goToEditComment")
	public String getTextComment(@RequestParam("commentId") String commentId,
								 @RequestParam("newsId") String newsId,
								 Model model) {
		try {
			CommentDataForEditBean comment = commentService.getCommentById(commentId);
			model.addAttribute("text", comment.getText());
			model.addAttribute("commentId", comment.getId());
			return "forward:/news/goToViewNews?id=" + newsId;
		}
		catch(ServiceException e) {
			return "redirect:/news/errorPage";
		}
	}

	@PostMapping("/doEditComment")
	public String editComment(@RequestParam("text") String text,
							  @RequestParam("commentId") String commentId,
							  @RequestParam("newsId") String newsId){
		try{
			commentService.editCommentById(commentId, text);
			return "redirect:/news/goToViewNews?id=" + newsId;
		}
		catch(ServiceException e){
			return "redirect:/news/errorPage";
		}
	}

	@GetMapping("/errorPage")
	public String errorPage(){
		return "error";
	}
}
package org.example.news_manager.controller;

import org.example.news_manager.dto.CommentDTO;
import org.example.news_manager.dto.NewsDTO;
import org.example.news_manager.dto.UserDTO;
import org.example.news_manager.service.CommentService;
import org.example.news_manager.service.NewsService;
import org.example.news_manager.service.UserService;
import org.example.news_manager.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/news")
public class FrontController {
	private final NewsService newsService;
	private final UserService userService;
	private final CommentService commentService;

	@Autowired
	public FrontController(NewsService newsService,
						   UserService userService,
						   CommentService commentService) {
		this.newsService = newsService;
		this.userService = userService;
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

			List<NewsDTO> news = newsService.getNewses("5", locale);
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
			List<NewsDTO> news = newsService.getNewses(locale);
			model.addAttribute("news", news);
			model.addAttribute("action", "newsList");
			return "baseLayout/baseLayout";
		} 
		catch (ServiceException e) {
			return "redirect:/news/errorPage";
		}
	}

	@PostMapping("/doAddNews")
	public String addNews(@ModelAttribute("news") NewsDTO news,
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
			NewsDTO news = newsService.findById(id, locale);
			List<CommentDTO> comments = commentService.getCommentsFromNewsById(id, locale);
			
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
			NewsDTO news = newsService.findById(id);
			model.addAttribute("news", news);
			model.addAttribute("action", "editNews");
			return "baseLayout/baseLayout";
		} 
		catch (ServiceException e) {
			return "redirect:/news/errorPage";
		}
	}

	@PostMapping("/doEditNews")
	public String doEditNews(@ModelAttribute("news") NewsDTO news,
							 @RequestParam("image") MultipartFile image) {
		try {
			newsService.editNews(news, image);
			return "redirect:/news/goToViewNews?id=" + news.getId();
		} 
		catch (ServiceException e) {
			return "redirect:/news/errorPage";
		}
	}

	@GetMapping("/goToAddNewsPage")
	public String showAddNewsPage(Model model) {
		NewsDTO news = new NewsDTO();
		model.addAttribute("news", news);
		model.addAttribute("action", "addNewsPage");
		return "baseLayout/baseLayout";
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
	
	
	/***********
	 ***********
	 ***USERS***
	 *********** 
	 ***********/

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

	@GetMapping("/goToRegistrationPage")
	public String showRegistrationPage(Model model) {
		UserDTO userBean = new UserDTO();
		model.addAttribute("user", userBean);
		model.addAttribute("action", "registrationPage");
		return "baseLayout/baseLayout";
	}

	@PostMapping("/doRegistrationUser")
	public String doRegistration(@ModelAttribute("user") UserDTO userBean,
								 @RequestParam("confirmPassword") String confirmPassword,
								 @RequestParam("localeName") String localeName) {
		try {
			userService.registration(userBean, confirmPassword, localeName);
			return "redirect:/news";
		}
		catch (ServiceException e) {
			return "redirect:/news/errorPage";
		}
	}

	@GetMapping("/doSignIn")
	public String doSignIn(@RequestParam("username") String login,
						   @RequestParam("password") String password,
						   HttpServletRequest request) {
		try {
			UserDTO userDTO = userService.signIn(login, password);
			Locale locale = userDTO.getLocale();
			HttpSession session = request.getSession(true);
			session.setAttribute("active", "true");
			session.setAttribute("role", userDTO.getRoleName());
			session.setAttribute("locale", locale);
			session.setAttribute("localization", locale.getLanguage());
			session.setAttribute("user", userDTO);
			return "redirect:/news/goToNewsList";
		} 
		catch (ServiceException e) {
			return "redirect:/news/errorPage";
		}
	}

	@GetMapping("/doSignOut")
	public String signOut(HttpServletRequest request) {
		try {
			request.getSession(true).invalidate();
			return "redirect:/news";
		} 
		catch (IllegalStateException e) {
			return "redirect:/news/errorPage";
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
							 @SessionAttribute("user") UserDTO user) {
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
			CommentDTO commentDTO = commentService.getCommentById(commentId);
			model.addAttribute("text", commentDTO.getText());
			model.addAttribute("commentId", commentDTO.getId());
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
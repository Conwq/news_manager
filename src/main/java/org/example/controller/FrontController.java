package org.example.controller;

import org.example.bean.News;
import org.example.bean.User;
import org.example.service.NewsService;
import org.example.service.UserService;
import org.example.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

@Controller
@RequestMapping("/news")
public class FrontController {
	private final NewsService newsService;
	private final UserService userService;
	
	@Autowired
	public FrontController(NewsService newsService, UserService userService) {
		this.newsService = newsService;
		this.userService = userService;
	}
	
	@RequestMapping
	public String goToBasePage(HttpServletRequest request,
							   Model model) {
		try {
			String localeParameter = (String) request.getSession().getAttribute("localization");
			Locale locale = localeParameter == null ? LocaleContextHolder.getLocale() : new Locale(localeParameter);

			List<News> news = newsService.getNewses("5", locale);
			model.addAttribute("news", news);
			model.addAttribute("action", "newsList");
			return "baseLayout/baseLayout";
		}
		catch(ServiceException e){
			return "";
		}
	}
	
	@RequestMapping("/goToNewsList")
	public String goToNewsList(@SessionAttribute("locale")Locale locale,
							   Model model) {
		try {
			List<News> news = newsService.getNewses(locale);
			model.addAttribute("news", news);
			model.addAttribute("action", "newsList");
			return "baseLayout/baseLayout";
		}
		catch(ServiceException e) {
			return "";
		}
	}

	@RequestMapping("/goToViewNews")
	public String showNews(@RequestParam("id") String id,
						   @SessionAttribute("locale") Locale locale,
						   Model model) {
		try {
			News news = newsService.findById(id, locale);
			model.addAttribute("news", news);
			model.addAttribute("action", "viewNews");
			return "baseLayout/baseLayout";
		}
		catch(ServiceException e) {
			return "";
		}
	}

	@RequestMapping("/goToEditNews")
	public String showEditNewsPage(@RequestParam("id") String id,
								   @SessionAttribute("locale") Locale locale,
								   Model model) {
		try {
			News news = newsService.findById(id, locale);
			model.addAttribute("news", news);
			model.addAttribute("action", "editNews");
			return "baseLayout/baseLayout";
		}
		catch(ServiceException e) {
			return "";
		}
	}

	@RequestMapping("/doEditNews")
	public String doEditNews(@ModelAttribute("news") News news) {
		try {
			newsService.editNews(news);
			return "redirect:/news/goToNewsList";
		}
		catch(ServiceException e) {
			return "";
		}
	}

	@RequestMapping("/doDeleteNews")
	public String doDeleteNews(@RequestParam("id") String id) {
		try {
			newsService.deleteNewsById(id);
			return "redirect:/news/goToNewsList";
		}
		catch(ServiceException e) {
			return "";
		}
	}

	@RequestMapping("/doDeleteSomeNews")
	public String deleteSomeNews(@RequestParam("news") String[] news){
		try {
			newsService.deleteNews(news);
			return "redirect:/news/goToNewsList";
		}
		catch(ServiceException e){
			return "";
		}
	}

	@RequestMapping("/goToAddNewsPage")
	public String showAddNewsPage(Model model){
		News news = new News();
		model.addAttribute("news", news);
		model.addAttribute("action", "addNewsPage");
		return "baseLayout/baseLayout";
	}

	@RequestMapping("/doAddNews")
	public String addNews(@ModelAttribute("news") News news){
		try {
			newsService.addNews(news);
			return "redirect:/news/goToNewsList";
		}
		catch (ServiceException e){
			return "";
		}
	}

	@RequestMapping("/goToRegistrationPage")
	public String showRegistrationPage(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		model.addAttribute("action", "registrationPage");
		return "baseLayout/baseLayout";
	}

	@RequestMapping("/doRegistrationUser")
	public String doRegistration(@ModelAttribute("user") User user,
								 @RequestParam("confirmPassword") String confirmPassword,
								 @RequestParam("country") String country) {
		try {

			userService.registration(user, confirmPassword, country);
			return "redirect:/news";
		}
		catch(ServiceException e) {
			return "";
		}
	}

	@RequestMapping("/doSignIn")
	public String doSignIn(@RequestParam("username") String login,
						   @RequestParam("password") String password,
						   HttpServletRequest request) {
		try {
			User user = userService.signIn(login, password);
			HttpSession session = request.getSession(true);
			session.setAttribute("active", "true");
			session.setAttribute("role", user.getRoleName());
			session.setAttribute("locale", user.getLocale());
			session.setAttribute("localization", user.getLocale().getLanguage());
			return "redirect:/news/goToNewsList";
		}
		catch(ServiceException e) {
			return "";
		}
	}

	@RequestMapping("/doSignOut")
	public String signOut(HttpServletRequest request) {
		try {
			request.getSession(true).invalidate();
			return "redirect:/news";
		}
		catch(IllegalStateException e) {
			return "";
		}
	}

	@RequestMapping("/changeLocale")
	public String changeLocale(HttpServletRequest request) throws URISyntaxException {
		request.getSession().setAttribute("localization", request.getParameter("localization"));
		request.getSession().setAttribute("locale", new Locale(request.getParameter("localization")));
		URI uri = new URI(request.getHeader("referer"));
		String path = uri.getPath();
		if(uri.getQuery() != null){
			path += "?" + uri.getQuery();
		}
		return "redirect:" + path;
	}
}